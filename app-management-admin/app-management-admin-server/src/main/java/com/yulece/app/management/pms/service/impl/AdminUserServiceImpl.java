package com.yulece.app.management.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lambdaworks.crypto.SCryptUtil;
import com.yulece.app.management.comments.api.CommentsMailApiService;
import com.yulece.app.management.comments.api.entity.EmailMessage;
import com.yulece.app.management.commons.utils.IPUtils;
import com.yulece.app.management.commons.utils.PojoConvertUtil;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.pms.constant.UserConstant;
import com.yulece.app.management.pms.dto.user.AdminUserResponse;
import com.yulece.app.management.pms.entity.AdminUser;
import com.yulece.app.management.pms.repository.AdminUserRepository;
import com.yulece.app.management.pms.service.AdminDeptService;
import com.yulece.app.management.pms.service.AdminUserService;
import com.yulece.app.management.pms.vo.user.AdminUserCreateRequest;
import com.yulece.app.management.pms.vo.user.AdminUserQueryRequest;
import com.yulece.app.management.pms.vo.user.AdminUserUpdateRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.yulece.app.management.pms.constant.UserConstant.USER_ACTIVE_MAIL_KEY;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserServiceImpl
 * @Package com.yulece.app.management.pms.service.impl
 * @Description:
 * @Date 2019-12-28 22:08
 **/
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final AdminDeptService adminDeptService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CommentsMailApiService commentsApiService;
    @Value("${HOST_WEBSITE}")
    private String hostWebsite;

    @Autowired
    public AdminUserServiceImpl(AdminUserRepository adminUserRepository,
                                AdminDeptService adminDeptService,
                                StringRedisTemplate stringRedisTemplate,
                                CommentsMailApiService commentsApiService) {
        this.adminUserRepository = adminUserRepository;
        this.adminDeptService = adminDeptService;
        this.stringRedisTemplate = stringRedisTemplate;
        this.commentsApiService = commentsApiService;
    }

    @Override
    public Integer save(AdminUserCreateRequest param, HttpServletRequest request) {
        //判断用户名是否存在
        if(isExistUserName(param,null)){
            throw new AppException(AppParamEnum.USER_USERNAME_EXIST_ERROR);
        }
        //判断手机号是是否存在
        if(isExistPhone(param,null)){
            throw new AppException(AppParamEnum.USER_PHONE_EXIST);
        }
        //判断邮箱是否存在
        if(isExistMail(param,null)){
            throw new AppException(AppParamEnum.USER_EMAIL_EXIST);
        }
        //判断部门是否存在
        if(isExistAdminDept(param.getUserDept())){
            throw new AppException(AppParamEnum.DEPT_NOT_EXIST);
        }
        //保存用户
        String userPassWord = SCryptUtil.scrypt(param.getPassWord(),32768,8,1);
        AdminUser adminUser = PojoConvertUtil.convertPojo(param, AdminUser.class);
        adminUser.setCreateTime(new Date());
        adminUser.setUpdateTime(new Date());
        adminUser.setOperateIp(IPUtils.getIp(request));
        adminUser.setPassWord(userPassWord);
        adminUser.setStatus(1);//未激活
        adminUserRepository.insert(adminUser);
        //生成激活码 保存到缓存 三天
        String randomCode = RandomStringUtils.randomAlphanumeric(6);
        //发送激活邮件
        asynchronouslySendMail(param,randomCode);
        //保存数据到redis
        stringRedisTemplate.opsForValue().set(
                String.format(USER_ACTIVE_MAIL_KEY,randomCode),
                adminUser.getMail(),
                7,
                TimeUnit.DAYS);

        //返回用户邮箱
        return adminUser.getUserId();
    }

    @Async
    void asynchronouslySendMail(AdminUserCreateRequest param, String randomCode) {
        try{
            String url ="http://"+hostWebsite+"/account/"+randomCode+"/active";
            String content =String.format(UserConstant.ACTIVATE_ACCOUNT_TMPL,
                    param.getNikeName(), url, url, url);
            EmailMessage emailMessage = new EmailMessage("支付平台邮箱激活验证",content,param.getMail());
            commentsApiService.sendMail(emailMessage);
        }catch (AppException e){
            throw new AppException(e);
        }

    }

    private boolean isExistAdminDept(Integer deptId) {
        return ObjectUtils.isEmpty(adminDeptService.findOne(deptId));
    }

    @Override
    public void update(AdminUserUpdateRequest param, HttpServletRequest request) {
        if(isExistUserName(param,param.getUserId())){
            throw new AppException(AppParamEnum.USER_USERNAME_EXIST_ERROR);
        }
        //判断手机号是是否存在
        if(isExistPhone(param,param.getUserId())){
            throw new AppException(AppParamEnum.USER_PHONE_EXIST);
        }
        //判断邮箱是否存在
        if(isExistMail(param,param.getUserId())){
            throw new AppException(AppParamEnum.USER_EMAIL_EXIST);
        }
        //判断部门是否存在
        if(isExistAdminDept(param.getUserDept())){
            throw new AppException(AppParamEnum.DEPT_NOT_EXIST);
        }
        AdminUser adminUser = PojoConvertUtil.convertPojo(param, AdminUser.class);
        adminUser.setUpdateTime(new Date());
        adminUser.setOperateIp(IPUtils.getIp(request));
        adminUserRepository.updateById(adminUser);
    }

    @Override
    public void activeAccount(String active) {
        //组装KEY
        String redisFormatKey = String.format(USER_ACTIVE_MAIL_KEY, active);
        String email = stringRedisTemplate.opsForValue().get(redisFormatKey);
        if (StringUtils.isEmpty(email)){
            throw new AppException(AppParamEnum.USER_ACTIVE_ERROR);
        }
        AdminUser adminUser = adminUserRepository.
                selectOne(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getMail, email)
                .eq(AdminUser::getStatus, 1));
        if(adminUser==null) {
            throw new AppException(AppParamEnum.USER_NOT_EXIST_ERROR);
        }
        AdminUser activeUser = new AdminUser();
        activeUser.setUserId(adminUser.getUserId());
        activeUser.setStatus(0);
        adminUserRepository.updateById(activeUser);
        stringRedisTemplate.delete(redisFormatKey);
    }

    @Override
    public IPage<AdminUserResponse> page(AdminUserQueryRequest request) {
        return adminUserRepository.findAllByPageAndObject(new Page<AdminUser>(request.getPageNo(),request.getPageSize()),request);
    }

    @Override
    public AdminUserResponse findOne(Integer userId) {
        AdminUser adminUser = adminUserRepository.selectById(userId);
        return PojoConvertUtil.convertPojo(adminUser,AdminUserResponse.class );
    }

    private boolean  isExistUserName(AdminUserCreateRequest param, Integer userId) {
        return adminUserRepository.selectList(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getUserName,param.getUserName())
                .ne(userId!=null,AdminUser::getUserId,userId)).size()>0;
    }

    private boolean isExistPhone(AdminUserCreateRequest param, Integer userId) {
        return adminUserRepository.selectList(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getPhone,param.getUserName())
                .ne(userId!=null,AdminUser::getUserId,userId)).size()>0;
    }

    private boolean isExistMail(AdminUserCreateRequest param, Integer userId) {
        return adminUserRepository.selectList(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getMail,param.getUserName())
                .ne(userId!=null,AdminUser::getUserId,userId)).size()>0;
    }
}
