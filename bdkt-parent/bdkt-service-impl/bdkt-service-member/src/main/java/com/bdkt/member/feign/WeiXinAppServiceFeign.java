package com.bdkt.member.feign;

import com.bdkt.entity.AppEntity;
import com.bdkt.service.WeiXinAppService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "bdkt-service-weixin")
public interface WeiXinAppServiceFeign extends WeiXinAppService {

	 /**
	 * 功能说明： 应用服务接口
	 */
	 @GetMapping("/getApp")
	 public AppEntity getApp();
}
