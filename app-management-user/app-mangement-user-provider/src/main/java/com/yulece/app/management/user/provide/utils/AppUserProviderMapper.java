package com.yulece.app.management.user.provide.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
public interface AppUserProviderMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
