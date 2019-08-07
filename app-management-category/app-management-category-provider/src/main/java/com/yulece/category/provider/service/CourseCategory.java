package com.yulece.category.provider.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: CourseCategory
 * @Package com.yulece.category.provider.service
 * @Description:
 * @Date 2019-08-07 21:37
 **/
@Entity
@Table(name = "course_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer categoryId;

    private String categoryName;
    private Integer categoryType;
    private Integer categoryTypeParent;
    private Date createTime;
    private Date updateTime;
}