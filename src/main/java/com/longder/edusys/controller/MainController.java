package com.longder.edusys.controller;

import com.google.code.kaptcha.Constants;
import com.longder.edusys.entity.po.GradeClass;
import com.longder.edusys.entity.po.SysRole;
import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.service.CaptchaService;
import com.longder.edusys.service.ClassManageService;
import com.longder.edusys.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserManageService userManageService;

    @Resource
    private ClassManageService classManageService;

    @Resource
    private CaptchaService captchaService;

    /**
     * 主页默认跳转到登陆页
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "redirect:toLogin";
    }

    /**
     * 去登陆页
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin(){
        logger.debug("去登陆页面!");
        return "login";
    }

    /**
     * 去注册页面
     * @return
     */
    @GetMapping("/toRegister")
    public String toRegister(Model model){
        //查询所有班级
        List<GradeClass> classList = classManageService.listAllClasses();
        model.addAttribute("classList",classList);
        return "register";
    }


    /**
     * 检查登录名
     * @return
     */
    @ResponseBody
    @PostMapping("/checkLoginName")
    public Boolean checkLoginName(String loginName){
        return userManageService.checkLoginName(loginName);
    }

    /**
     * 校验验证码
     * @param code
     * @return
     */
    @ResponseBody
    @PostMapping("/checkCode")
    public Boolean checkCode(String code, HttpServletRequest request){
        //原验证码
        String source = (String) WebUtils.getSessionAttribute(request, Constants.KAPTCHA_SESSION_KEY);
        return captchaService.checkCaptchaCode(code,source);
    }

    /**
     * 注册一个用户
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public String register(SysUser sysUser){
        logger.debug("开始注册用户，登录名：{}",sysUser.getLoginName());
        userManageService.saveOneUser(sysUser,sysUser.getRole());
        return "redirect:toLogin";
    }

    /**
     * 后台主页,登陆后访问的首页
     * @return
     */
    @GetMapping("/admin/index")
    public String adminIndex(){
        logger.debug("去后台主页");
        return "index";
    }

    /**
     * 仪表盘，欢迎页面
     * @return
     */
    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
