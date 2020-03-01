package com.longder.edusys.controller;

import com.longder.edusys.entity.enums.QuestionType;
import com.longder.edusys.entity.po.Question;
import com.longder.edusys.repository.QuestionRepository;
import com.longder.edusys.service.ChapterManageService;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目管理业务的控制器
 */
@Controller
@RequestMapping("/admin/question")
public class QuestionManageController {

    @Resource
    private ChapterManageService chapterManageService;
    @Resource
    private QuestionManageService questionManageService;

    /**
     * 习题管理列表
     * @return
     */
    @GetMapping("/list")
    public String toQuestionList(Model model){
        List<Question> questionList = questionManageService.listAllQuestion();
        model.addAttribute("questionList",questionList);
        return "question/list";
    }

    /**
     * 去新增习题页面
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddQuestion(Model model){
        model.addAttribute("questionTypes", QuestionType.values());
        model.addAttribute("chapterList",chapterManageService.listAllChapter());
        return "question/add-question-modal";
    }

    /**
     * 添加习题
     * @param question
     * @return
     */
    @PostMapping("/add")
    public String addQuestion(Question question){
        questionManageService.addOneQuestion(question);
        return "redirect:/admin/question/list";
    }
}
