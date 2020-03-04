package com.longder.edusys.controller;

import com.longder.edusys.entity.dto.ExamInitDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 考试管理相关业务控制器
 */
@Controller
@RequestMapping("/admin/exam")
public class ExamManageController {

    /**
     * 初始化考试
     * @return
     */
    @PostMapping("/init")
    public String initExam(ExamInitDto dto){
        System.out.println(dto);
        return null;
    }
}
