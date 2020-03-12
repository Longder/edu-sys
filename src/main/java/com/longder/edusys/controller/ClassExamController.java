package com.longder.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 班级考试管理
 */
@Controller
@RequestMapping("/admin/classExam")
public class ClassExamController {

    /**
     * 定制选择考试
     * @return
     */
    @RequestMapping("/assign")
    public String init(){
        return null;
    }

    /**
     * 班级考试列表
     * @return
     */
    public String listExam(){
        return null;
    }

}
