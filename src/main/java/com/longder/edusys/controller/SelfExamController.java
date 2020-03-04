package com.longder.edusys.controller;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.service.ChapterManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自我测验相关业务的Controller
 */
@Controller
@RequestMapping("/admin/selfExam")
public class SelfExamController {

    @Resource
    private ChapterManageService chapterManageService;

    /**
     * 自我测验选择页
     * @param model
     * @return
     */
    @GetMapping("/choose")
    public String choose(Model model){
        List<Chapter> chapterList = chapterManageService.listAllChapter();
        model.addAttribute("chapterList",chapterList);
        return "selfExam/choose";
    }

}
