package com.bdkt.service.impl;

import com.bdkt.entity.AppEntity;
import com.bdkt.service.WeiXinAppService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务接口的实现
 */
@RestController
public class WeiXinAppServiceImpl implements WeiXinAppService {

//	@GetMapping("/getApp")
	public AppEntity getApp() {
		return new AppEntity("644064779", "yushengjun644");
	}
}
