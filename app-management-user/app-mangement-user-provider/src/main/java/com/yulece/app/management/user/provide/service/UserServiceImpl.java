package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.commons.enums.ParamEnum;
import com.yulece.app.management.commons.exception.AppException;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import com.yulece.app.management.user.provide.pojo.AdminUser;
import com.yulece.app.management.user.provide.repositories.AdminUserRepository;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;

    @Autowired
    public UserServiceImpl(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public AdminUserVo getById(Integer id) {
        AdminUser adminUser = adminUserRepository.findOne(id);
        AdminUserVo adminUserVo = PojoConvertUtil.convertPojo(adminUser, AdminUserVo.class);
        return adminUserVo;
    }

    @Override
    public boolean update(AdminUserParam param) {
        BeanValidator.check(param);
        //查询是否存在该用户
        AdminUser adminUser = adminUserRepository.selectByPrimaryKey(param.getUserId());
        if (!ObjectUtils.allNotNull(adminUser)) {
            throw new AppException(ParamEnum.USER_NOT_EXIST_ERROR);
        }
        //拷贝用户
        AdminUser updateUser = PojoConvertUtil.convertPojo(param, AdminUser.class);
        updateUser.setUserName(adminUser.getUserName());
        //TODO 后续补充
        int count = adminUserRepository.insertSelective(updateUser);
        if(count > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean create(AdminUserParam param) {

        return false;
    }

    @Override
    public void active(String key) {

    }
}
