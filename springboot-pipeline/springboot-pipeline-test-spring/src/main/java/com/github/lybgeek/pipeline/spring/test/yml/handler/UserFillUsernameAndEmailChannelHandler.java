package com.github.lybgeek.pipeline.spring.test.yml.handler;


import com.github.lybgeek.pipeline.context.ChannelHandlerContext;
import com.github.lybgeek.pipeline.handler.AbstactChannelHandler;
import com.github.lybgeek.pipeline.model.ChannelHandlerRequest;
import com.github.lybgeek.pipeline.spring.test.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

@Slf4j
public class UserFillUsernameAndEmailChannelHandler extends AbstactChannelHandler {
    @SneakyThrows
    @Override
    public boolean handler(ChannelHandlerContext chx) {
        ChannelHandlerRequest channelHandlerRequest = chx.getChannelHandlerRequest();
        System.out.println("yml------------------------------------步骤二：用户名以及邮箱填充【将汉语转成拼音填充】【"+channelHandlerRequest.getRequestId()+"】");
        Object params = channelHandlerRequest.getParams();
        if(params instanceof User){
            User user = (User)params;
            String fullname = user.getFullname();
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
            hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            String username = PinyinHelper.toHanYuPinyinString(fullname, hanyuPinyinOutputFormat);
            user.setUsername(username);
            user.setEmail(username + "@qq.com");
            return true;
        }


        return false;
    }
}
