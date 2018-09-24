package com.yulece.app.management.user.api;

import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("app-management-user-provider")
public interface AdminUserService {

    /**
     * 查询用户
     *
     * @param id 用户id
     * @return {@AdminUserVo}
     */
    @GetMapping("/user/{id}")
    AdminUserVo getById(@PathVariable("id") Integer id);

    /**
     * 更新数据
     * 可以更新的内容为 头像 状态 备注
     * 不可以更新 用户名 手机号 邮箱
     * 更新成功返回:<code>true</code>
     * 更新失败返回:<code>false</code>
     * @param param
     * @return
     */
    @PutMapping("/user/update")
    boolean update(AdminUserParam param);

    /**
     * 添加职员
     *
     * @param param
     * @return
     */
    @PostMapping("/user/save")
    boolean create(AdminUserParam param);

    /**
     * 通过邮箱激活
     *
     * @param key
     */
    @PutMapping("/user/active")
    void active(String key);

}
