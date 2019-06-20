package com.bdkt.member.service.impl;

import com.bdkt.entity.AppEntity;
import com.bdkt.member.feign.WeiXinAppServiceFeign;
import com.bdkt.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员服务接口实现
 */
@RestController
public class MemberServiceImpl implements MemberService {
	@Autowired
	private WeiXinAppServiceFeign weiXinAppServiceFeign;

	@GetMapping("/memberInvokeWeixin")
	public AppEntity memberInvokeWeixin() {
		return weiXinAppServiceFeign.getApp();
	}

}
