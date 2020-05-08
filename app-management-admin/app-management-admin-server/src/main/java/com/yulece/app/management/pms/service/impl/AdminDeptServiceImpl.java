package com.yulece.app.management.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.IPUtils;
import com.yulece.app.management.commons.utils.PojoConvertUtil;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.pms.dto.dept.AdminDeptListResponse;
import com.yulece.app.management.pms.entity.tree.AdminDeptTreeDto;
import com.yulece.app.management.pms.entity.AdminDept;
import com.yulece.app.management.pms.repository.AdminDeptRepository;
import com.yulece.app.management.pms.service.AdminDeptService;
import com.yulece.app.management.pms.vo.dept.AdminDeptCreateRequest;
import com.yulece.app.management.pms.vo.dept.AdminDeptQueryRequest;
import com.yulece.app.management.pms.vo.dept.AdminDeptUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptServiceImpl
 * @Package com.yulece.app.management.pms.service
 * @Description:
 * @Date 2019-11-09 19:18
 **/
@Service
public class AdminDeptServiceImpl implements AdminDeptService {

    private final AdminDeptRepository adminDeptRepository;

    @Autowired
    public AdminDeptServiceImpl(AdminDeptRepository adminDeptRepository) {
        this.adminDeptRepository = adminDeptRepository;
    }

    @Override
    public Integer create(HttpServletRequest request, AdminDeptCreateRequest param) {
        BeanValidator.check(param);
        if(checkExistSort(param.getDeptSeq(),param.getDeptParentId(),null)){
            throw new AppException(AppParamEnum.DEPT_EXIST_SORT);
        }
        if(checkExistDeptName(param.getDeptName(),param.getDeptParentId(),null)){
            throw new AppException(AppParamEnum.DEPT_NAME_EXIST_ERROR);
        }
        //获取上级服务信息
        AdminDept dept = PojoConvertUtil.convertPojo(param, AdminDept.class);
        dept.setDeptLevel(AdminDeptCreateRequest.LEVEL);
        AdminDept selectById = adminDeptRepository.selectOne(new QueryWrapper<AdminDept>().lambda()
        .eq(AdminDept::getDeptId,param.getDeptParentId()));
        if(!ObjectUtils.isEmpty(selectById)){
            dept.setDeptLevel(param.getLEVEL(selectById.getDeptLevel()));
        }
        dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());
        dept.setOperateIp(IPUtils.getIp(request));
        adminDeptRepository.insert(dept);
        return dept.getDeptId();
    }

    private boolean checkExistDeptName(String deptName, Integer deptParentId, Integer deptId) {
        return adminDeptRepository.selectList(new QueryWrapper<AdminDept>().lambda()
                .eq(true,AdminDept::getDeptName,deptName)
                .eq(true,AdminDept::getDeptParentId,deptParentId)
                .ne(deptId!=null,AdminDept::getDeptId,deptId)).size()>0;
    }

    private boolean checkExistSort(Integer deptSeq, Integer deptParentId,Integer deptId) {
        return adminDeptRepository.selectList(new QueryWrapper<AdminDept>().lambda()
                .eq(true,AdminDept::getDeptSeq,deptSeq)
                .eq(true,AdminDept::getDeptParentId,deptParentId)
                .ne(deptId!=null,AdminDept::getDeptId,deptId)).size()>0;
    }

    @Override
    @Transactional
    public Integer update(HttpServletRequest httpServletRequest, AdminDeptUpdateRequest param) {
        BeanValidator.check(param);
        //查询是否  旧数据
        AdminDept oldDept = adminDeptRepository.selectById(param.getDeptId());
        BeanValidator.checkObjectNull(oldDept,AppParamEnum.DEPT_NOT_EXIST);
        if(checkExistSort(param.getDeptSeq(),param.getDeptParentId(),param.getDeptId())){
            throw new AppException(AppParamEnum.DEPT_EXIST_SORT);
        }
        if(checkExistDeptName(param.getDeptName(),param.getDeptParentId(),param.getDeptId())){
            throw new AppException(AppParamEnum.DEPT_NAME_EXIST_ERROR);
        }
        AdminDept updateAdminDept = new AdminDept();
        BeanUtils.copyProperties(param,updateAdminDept);
        if(!param.getDeptParentId().equals(oldDept.getDeptParentId())){
            //获取上级目录等级
            AdminDept parentAdminDept = adminDeptRepository.selectById(param.getDeptParentId());
            updateAdminDept.setDeptLevel(param.getLEVEL(parentAdminDept.getDeptLevel()));
            //获取下级目录
            List<AdminDept> adminDepts = adminDeptRepository.selectList(
                    new QueryWrapper<AdminDept>().lambda()
                .likeRight(AdminDept::getDeptLevel, oldDept.getDeptLevel() + ","));
            if(!CollectionUtils.isEmpty(adminDepts)){
                adminDepts.forEach(item->{
                    AdminDept adminDept = new AdminDept();
                    adminDept.setDeptId(item.getDeptId());
                    adminDept.setDeptLevel(updateAdminDept.getDeptLevel()
                            .concat(item.getDeptLevel().substring(oldDept.getDeptLevel().length())));
                    adminDeptRepository.updateById(adminDept);
                });
            }
        }
        updateAdminDept.setUpdateTime(new Date());
        adminDeptRepository.updateById(updateAdminDept);
        return updateAdminDept.getDeptId();
    }

    @Override
    public IPage<AdminDeptListResponse> list(AdminDeptQueryRequest request) {
        return adminDeptRepository.findAllByPageAndObject(new Page<AdminDept>(request.getPageNo(),request.getPageSize()),request);
    }

    @Override
    public List<AdminDeptTreeDto> deptTree() {
        List<AdminDept> allList = adminDeptRepository.selectList(null);
        List<AdminDeptTreeDto> dtoList = Lists.newArrayList();
        for (AdminDept dept : allList) {
            AdminDeptTreeDto dto = dept.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    @Override
    public AdminDeptListResponse findOne(Integer deptId) {
        AdminDept adminDept = adminDeptRepository.selectById(deptId);
        return PojoConvertUtil.convertPojo(adminDept,AdminDeptListResponse.class);
    }

    private List<AdminDeptTreeDto> deptListToTree(List<AdminDeptTreeDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, ...] Map<String, List<Object>>
        Multimap<String, AdminDeptTreeDto> levelDeptMap = ArrayListMultimap.create();
        List<AdminDeptTreeDto> rootList = Lists.newArrayList();

        for (AdminDeptTreeDto dto : dtoList) {
            levelDeptMap.put(dto.getDeptLevel(), dto);
            if (AdminDeptCreateRequest.LEVEL.equals(dto.getDeptLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<AdminDeptTreeDto>() {
            public int compare(AdminDeptTreeDto o1, AdminDeptTreeDto o2) {
                return o1.getDeptSeq() - o2.getDeptSeq();
            }
        });
        // 递归生成树
        transformDeptTree(rootList, AdminDeptCreateRequest.LEVEL, levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<AdminDeptTreeDto> rootList, String level, Multimap<String, AdminDeptTreeDto> levelDeptMap) {
        for (int i = 0; i < rootList.size(); i++) {
            // 遍历该层的每个元素
            AdminDeptTreeDto deptLevelDto = rootList.get(i);
            // 处理当前层级的数据
            String nextLevel = AdminDeptCreateRequest.getLEVEL(level, deptLevelDto.getDeptId());
            // 处理下一层
            List<AdminDeptTreeDto> tempDeptList = (List<AdminDeptTreeDto>) levelDeptMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setTrees(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }
    private Comparator<AdminDeptTreeDto> deptSeqComparator = new Comparator<AdminDeptTreeDto>() {
        public int compare(AdminDeptTreeDto o1, AdminDeptTreeDto o2) {
            return o1.getDeptSeq() - o2.getDeptSeq();
        }
    };

}
