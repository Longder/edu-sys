package com.longder.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 教师管理业务的控制器
 */
@Controller
@RequestMapping("/admin/teacher")
public class TeacherManageController {


    /**
     * 教师列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String toTeacherList(Model model){

        return null;
    }


}
