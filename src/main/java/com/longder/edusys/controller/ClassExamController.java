package com.longder.edusys.controller;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.service.ChapterManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级考试管理
 */
@Controller
@RequestMapping("/admin/classExam")
public class ClassExamController {

    @Resource
    private ChapterManageService chapterManageService;

    /**
     * 定制选择考试
     * @return
     */
    @RequestMapping("/init")
    public String init(Model model){
        List<Chapter> chapterList = chapterManageService.listAllChapter();
        model.addAttribute("chapterList",chapterList);
        return "classExam/init";
    }

    /**
     * 班级考试列表
     * @return
     */
    public String listExam(){
        return null;
    }

}
