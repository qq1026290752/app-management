package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.comments.api.CommentsApiService;
import com.yulece.app.management.comments.api.entity.EmailMessage;
import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.ExceptionEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import com.yulece.app.management.user.provide.constant.AdminConstant;
import com.yulece.app.management.user.provide.enums.AdminUserStatusEnum;
import com.yulece.app.management.user.provide.pojo.AdminUser;
import com.yulece.app.management.user.provide.properties.AppAdminProperties;
import com.yulece.app.management.user.provide.repositories.AdminUserRepository;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements AdminUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppAdminProperties appAdminProperties;

    private final CommentsApiService commentsApiService;
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public UserServiceImpl(CommentsApiService commentsApiService, AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder, StringRedisTemplate stringRedisTemplate) {
        this.commentsApiService = commentsApiService;
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public AdminUserVo getById(Integer id) {
        AdminUser adminUser = adminUserRepository.findOne(id);
        if(adminUser == null){
            throw new AppException(ExceptionEnum.QUERY_ERROR);
        }
        AdminUserVo adminUserVo = PojoConvertUtil.convertPojo(adminUser, AdminUserVo.class);
        return adminUserVo;
    }

    @Override
    public boolean  update(AdminUserParam param) {
        BeanValidator.check(param);
        //查询是否存在该用户
        AdminUser adminUser = adminUserRepository.selectByPrimaryKey(param.getUserId());
        if (!ObjectUtils.allNotNull(adminUser)) {
            throw new AppException(AppParamEnum.USER_NOT_EXIST_ERROR);
        }
        //拷贝用户
        AdminUser updateUser = PojoConvertUtil.convertPojo(param, AdminUser.class);
        //防止修改
        updateUser.setUserName(adminUser.getUserName());
        updateUser.setPassWord(adminUser.getPassWord());
        updateUser.setStatus(adminUser.getStatus());
        updateUser.setTelephone(adminUser.getTelephone());
        updateUser.setMail(adminUser.getMail());
        updateUser.setOperateIp(LoginHandlerInterceptor.getCurrentIp());
        updateUser.setOperator(LoginHandlerInterceptor.getCurrentUser());
        int count = adminUserRepository.updateByPrimaryKey(updateUser);
        return count > 0;
    }

    @Override
    public boolean create(AdminUserParam param) {
        BeanValidator.check(param);
        //查询用户名是否重复
        if (findUserNameIsExist(param.getUserId(), param.getUserName())) {
            throw new AppException(AppParamEnum.USER_USERNAME_EXIST_ERROR);
        }
        //查询手机号是否重复
        if (findMobilePhoneIsExist(param.getUserId(), param.getTelephone())) {
            throw new AppException(AppParamEnum.USER_TLEL_PHONE_EXIST_RROE);
        }
        //查询邮箱是否重复
        if (findEmailIsExist(param.getUserId(), param.getMail())) {
            throw new AppException(AppParamEnum.USER_EMAI_EXIST_ERROE);
        }
        //拷贝用户
        AdminUser adminUser = PojoConvertUtil.convertPojo(param, AdminUser.class);
        //随机产生用户密码
        String password = RandomStringUtils.randomAlphanumeric(8);
        //后期补充
        adminUser.setOperateIp(LoginHandlerInterceptor.getCurrentUser());
        adminUser.setOperator(LoginHandlerInterceptor.getCurrentIp());
        adminUser.setPassWord(passwordEncoder.encode(password));
        adminUser.setStatus(AdminUserStatusEnum.USER_NON_ACTIVATED.getCode());
        int saveCount = adminUserRepository.insertSelective(adminUser);
        registerNotify(adminUser.getMail(),password);
        return saveCount != 0;
    }

    private boolean findEmailIsExist(Integer userId, String mail) {
        AdminUserParam adminUserParam = new AdminUserParam();
        adminUserParam.setMail(mail);
        adminUserParam.setUserId(userId);
        return adminUserRepository.countUserByUserParam(adminUserParam) > 0;
    }

    private boolean findMobilePhoneIsExist(Integer userId, String telephone) {
        AdminUserParam adminUserParam = new AdminUserParam();
        adminUserParam.setTelephone(telephone);
        adminUserParam.setUserId(userId);
        return adminUserRepository.countUserByUserParam(adminUserParam) > 0;
    }

    private boolean findUserNameIsExist(Integer userId, String userName) {
        AdminUserParam adminUserParam = new AdminUserParam();
        adminUserParam.setUserName(userName);
        adminUserParam.setUserId(userId);
        return adminUserRepository.countUserByUserParam(adminUserParam) > 0;
    }

    @Override
    public boolean active(String key) {
        String eMail = stringRedisTemplate.opsForValue().get(String.format(AdminConstant.ACTIVE_MAIL_KEY, key));
        if (StringUtils.isBlank(eMail)) {
            throw new AppException(AppParamEnum.USER_ACTIVE_KEY_ERROR);
        }
        //根据邮箱查询用户
        AdminUser user = adminUserRepository.findAdminUserByMail(eMail);
        if (user == null) {
            throw new AppException(AppParamEnum.USER_NOT_EXIST_ERROR);
        }
        user.setStatus(AdminUserStatusEnum.USER_ACTIVATED.getCode());
        adminUserRepository.updateByPrimaryKey(user);
        stringRedisTemplate.delete(String.format(AdminConstant.ACTIVE_MAIL_KEY, key));
        LOGGER.info("账户[{}]激活",user.getUserName());
        return true;
    }

    /**
     * 发送邮件激活用户
     *
     * @param mail
     */
    private void registerNotify(String mail, String password) {
        /**
         * 1.缓存KEY与MAIL的关系
         * 2.交给异步项目发送邮件
         */
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        //激活URL
        String address = appAdminProperties.getServerProperties().getDomainName().concat(":").concat(String.valueOf(appAdminProperties.getServerProperties().getDomainPort()));
        String activateUrl = address + "/app/account/active?key=" + randomKey;
        String context = "尊敬的用户: \n\t您登陆本平台的密码是:" + password + ".\n\t" +
                "\t\t\t\t请点击如下网址激活: " + activateUrl + "\n三天内有效";

        //存储到REDIS
        stringRedisTemplate.opsForValue().append(String.format(AdminConstant.ACTIVE_MAIL_KEY, randomKey), mail);
        stringRedisTemplate.expire(String.format(AdminConstant.ACTIVE_MAIL_KEY, randomKey), 3, TimeUnit.DAYS);
        EmailMessage message = new EmailMessage("娱乐侧后台管理账户验证", context, mail);
        commentsApiService.sendMail(message);
    }
}