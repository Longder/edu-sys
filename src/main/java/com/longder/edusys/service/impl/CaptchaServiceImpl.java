package com.longder.edusys.service.impl;

import com.google.code.kaptcha.Producer;
import com.longder.edusys.entity.dto.Captcha;
import com.longder.edusys.service.CaptchaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private Producer captchaProducer;

    /**
     * 随机生成一个验证码
     * @return
     */
    @Override
    public Captcha createCaptcha() {
        String capText = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(capText);
        return new Captcha(capText,image);
    }

    /**
     * 校验验证码
     *
     * @param valid 待校验
     * @param source 原验证码
     * @return true 验证通过 false 验证不通过
     */
    @Override
    public Boolean checkCaptchaCode(String valid, String source) {
        boolean flag = false;
        if(valid!=null){
            flag = source.equalsIgnoreCase(valid.trim());
        }
        return flag;
    }
}
