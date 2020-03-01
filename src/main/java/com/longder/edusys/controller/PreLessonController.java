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
 * 预习功能的控制器
 */
@Controller
@RequestMapping("/admin/preLesson")
public class PreLessonController {

    @Resource
    private ChapterManageService chapterManageService;

    /**
     * 章节列表页
     * @param model
     * @return
     */
    @GetMapping("/listChapter")
    public String listChapter(Model model){
        List<Chapter> chapterList = chapterManageService.listAllChapter();
        model.addAttribute("chapterList",chapterList);
        return "preLesson/chapter-list";
    }

    /**
     * 某章节详情
     * @param model
     * @param chapterId
     * @return
     */
    @GetMapping("/detail")
    public String chapterDetail(Model model,Long chapterId){
        model.addAttribute("chapter",chapterManageService.getOneChapter(chapterId));
        return "preLesson/chapter-detail-modal";
    }
}
