package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "app-management-user-provider",configuration = FeignHeaderInterceptor.class)
public interface AdminAclModelService {
}
