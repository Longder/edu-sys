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
}
