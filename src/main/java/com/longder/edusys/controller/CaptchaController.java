package com.longder.edusys.controller;

import com.google.code.kaptcha.Constants;
import com.longder.edusys.entity.dto.Captcha;
import com.longder.edusys.service.CaptchaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 生成验证码的控制器
 */
@Controller
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 获取随机验证码，用流的方式写到response中
     * @param response
     * @param session
     */
    @GetMapping("/getCaptcha")
    public void createCaptcha(HttpServletResponse response, HttpSession session) {
        Captcha captcha = captchaService.createCaptcha();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,captcha.getCode());
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control","post-check=0, pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");
        try{
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(captcha.getCaptchaImage(),"jpg",out);
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
