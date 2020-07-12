package com.yulece.app.management.pms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.dto.dept.AdminDeptListResponse;
import com.yulece.app.management.pms.dto.dept.AdminDeptTreeDto;
import com.yulece.app.management.pms.service.AdminDeptService;
import com.yulece.app.management.pms.vo.dept.AdminDeptCreateRequest;
import com.yulece.app.management.pms.vo.dept.AdminDeptQueryRequest;
import com.yulece.app.management.pms.vo.dept.AdminDeptUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptController
 * @Package com.yulece.app.management.pms.controller
 * @Description:
 * @Date 2019-11-17 09:35
 **/
@RestController
@RequestMapping("/dept")
public class AdminDeptController {

    private final AdminDeptService adminDeptService;
    @Autowired
    public AdminDeptController(AdminDeptService adminDeptService) {
        this.adminDeptService = adminDeptService;
    }

    @PostMapping
    public ResultVo create(HttpServletRequest request, @RequestBody AdminDeptCreateRequest params){
        adminDeptService.create(request, params);
        return ResultVo.createSuccessResult();
    }

    @PutMapping
    public ResultVo update(HttpServletRequest request, @RequestBody AdminDeptUpdateRequest params){
        adminDeptService.update(request, params);
        return ResultVo.createSuccessResult();
    }

    @PostMapping("/list")
    public ResponseEntity<IPage<AdminDeptListResponse>> list(@RequestBody AdminDeptQueryRequest request){
        IPage<AdminDeptListResponse> list = adminDeptService.list(request);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/trees")
    public ResponseEntity<List<AdminDeptTreeDto>> tree(){
        List<AdminDeptTreeDto> deptTree = adminDeptService.deptTree();
        return ResponseEntity.ok(deptTree);
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<AdminDeptListResponse> findOne(@PathVariable Integer deptId){
        AdminDeptListResponse response = adminDeptService.findOne(deptId);
        return ResponseEntity.ok(response);
    }

}
