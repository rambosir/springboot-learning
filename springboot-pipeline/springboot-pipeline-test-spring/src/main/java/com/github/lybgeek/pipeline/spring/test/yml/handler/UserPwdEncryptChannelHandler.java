package com.github.lybgeek.pipeline.spring.test.yml.handler;


import cn.hutool.crypto.digest.DigestUtil;
import com.github.lybgeek.pipeline.context.ChannelHandlerContext;
import com.github.lybgeek.pipeline.handler.AbstactChannelHandler;
import com.github.lybgeek.pipeline.model.ChannelHandlerRequest;
import com.github.lybgeek.pipeline.spring.test.model.User;

public class UserPwdEncryptChannelHandler extends AbstactChannelHandler {
    @Override
    public boolean handler(ChannelHandlerContext chx) {
        ChannelHandlerRequest channelHandlerRequest = chx.getChannelHandlerRequest();
        System.out.println("yml------------------------------------步骤三：用户密码明文转密文【"+channelHandlerRequest.getRequestId()+"】");
        Object params = channelHandlerRequest.getParams();
        if(params instanceof User){
            String encryptPwd = DigestUtil.sha256Hex(((User) params).getPassword());
            ((User) params).setPassword(encryptPwd);
            return true;
        }

        return false;
    }
}
