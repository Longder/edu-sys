package com.longder.edusys.controller;

import com.longder.edusys.entity.enums.SubjectType;
import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.service.ChapterManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 章节管理类业务的控制器
 */
@Controller
@RequestMapping("/admin/chapter")
public class ChapterManageController {

    @Resource
    private ChapterManageService chapterManageService;

    /**
     * 学科章节列表
     * @return
     */
    @GetMapping("/list")
    public String toChapterList(Model model){
        List<Chapter> chapterList = chapterManageService.listAllChapter();
        model.addAttribute("chapterList",chapterList);
        return "chapter/list";
    }

    /**
     * 去添加章节页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddChapter(Model model){
        model.addAttribute("subjectTypes", SubjectType.values());
        return "chapter/add-chapter-modal";
    }

    /**
     * 添加章节
     * @return
     */
    @PostMapping("/add")
    public String addChapter(Chapter chapter){
        chapterManageService.addOneChapter(chapter);
        return "redirect:/admin/chapter/list";
    }
}
