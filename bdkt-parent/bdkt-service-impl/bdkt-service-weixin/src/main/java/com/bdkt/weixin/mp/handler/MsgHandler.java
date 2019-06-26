package com.bdkt.weixin.mp.handler;

import com.bdkt.core.constants.Constants;
import com.bdkt.core.utils.RedisUtil;
import com.bdkt.core.utils.RegexUtils;
import com.bdkt.weixin.mp.builder.TextBuilder;
import com.bdkt.weixin.mp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {
    // 用户发送手机验证码提示
    @Value("${wx.registion.code-message}")
    private String registrationCodeMessage;
    // 默认用户发送验证码提示
    @Value("${wx.registion.default-code-message}")
    private String defaultRegistrationCodeMessage;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
        // 1.获取客户端发送的消息
        String fromContent = wxMessage.getContent();//手机号
        // 2.如果客户端发送消息为手机号码，则发送验证码
        if (RegexUtils.checkMobile(fromContent)) {
            // 3.生成随机四位注册码
            int registCode = registCode();
            String content = String.format(registrationCodeMessage, registCode);
            // 4.将验证码存放在Redis中
            redisUtil.setForTimeMIN(Constants.WEIXINCODE_KEY + fromContent, registCode + "", Constants.WEIXINCODE_TIMEOUT);
            return new TextBuilder().build(content, wxMessage, weixinService);
        }
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);//否则传默认的值过去
    }
    // 获取注册码
    private int registCode() {
        int registCode = (int) (Math.random() * 9000 + 1000);
        return registCode;
    }
}
