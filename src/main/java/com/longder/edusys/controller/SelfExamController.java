package com.longder.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自我测验相关业务的Controller
 */
@Controller
@RequestMapping("/admin/selfExam")
public class SelfExamController {



    /**
     * 自我测验选择页
     * @param model
     * @return
     */
    @GetMapping("/choose")
    public String choose(Model model){
        return "selfExam/choose";
    }

}
