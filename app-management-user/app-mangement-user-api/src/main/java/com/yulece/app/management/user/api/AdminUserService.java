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
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    AdminUserVo getById(@PathVariable("id") Integer id);

    /**
     * 查询公司全部用户
     *
     * @param params
     * @param pageable
     * @return
     */
    //@GetMapping("/user/list")
    //page select(AdminUserVo params, Pageable pageable);

    /**
     * 更新某个职员资料
     *
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
