package com.longder.edusys.controller;

import com.longder.edusys.entity.dto.ExamInitDto;
import com.longder.edusys.entity.dto.ExamSubmitDto;
import com.longder.edusys.entity.po.ExamPaper;
import com.longder.edusys.service.ExamManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 考试管理相关业务控制器
 */
@Controller
@RequestMapping("/admin/exam")
public class ExamManageController {

    @Resource
    private ExamManageService examManageService;

    /**
     * 初始化考试
     * 自测盒考试都是
     * @return
     */
    @PostMapping("/init")
    public String initExam(ExamInitDto dto, Model model){
        System.out.println(dto);
        ExamPaper examPaper = examManageService.generateExam(dto);
        model.addAttribute("exam",examPaper);
        return "exam/exam-paper";
    }

    /**
     * 完成考试
     * @return
     */
    @PostMapping("/complete")
    public String completeExam(ExamSubmitDto dto){
        examManageService.completeExam(dto);
        return null;
    }
}
