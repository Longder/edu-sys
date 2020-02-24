package com.longder.edusys.service;

import com.longder.edusys.entity.dto.Captcha;

/**
 * 验证码的Service
 */
public interface CaptchaService {

    /**
     * 随机生成一个验证码
     * @return
     */
    Captcha createCaptcha();
}
