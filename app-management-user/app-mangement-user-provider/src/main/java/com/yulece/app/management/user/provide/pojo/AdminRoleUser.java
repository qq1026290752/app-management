package com.yulece.app.management.user.provide.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class AdminRoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleUserId;

    private Integer roleId;

    private Integer userId;

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;

    private Integer roleStatus;


}