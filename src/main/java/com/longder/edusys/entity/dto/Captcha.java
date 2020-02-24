package com.longder.edusys.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 验证码对象
 */
@Data
@AllArgsConstructor
public class Captcha implements Serializable {

    /**
     * 验证码的值
     */
    private String code;

    /**
     * 对应的验证码图片
     */
    private BufferedImage captchaImage;
}
