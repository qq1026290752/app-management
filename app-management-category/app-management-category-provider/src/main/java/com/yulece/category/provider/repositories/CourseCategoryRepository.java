package com.yulece.category.provider.repositories;

import com.yulece.category.provider.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: CourseCategoryRepository
 * @Package com.yulece.category.provider.repositories
 * @Description:
 * @Date 2019-08-07 22:08
 **/
@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory,Integer> {
}
