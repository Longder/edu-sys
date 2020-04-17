package com.longder.edusys.controller;

import com.longder.edusys.entity.enums.QuestionType;
import com.longder.edusys.entity.po.Question;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 去编辑习题
     * @param model
     * @return
     */
    @GetMapping("/toEdit")
    public String toEditQuestion(Model model,Long questionId){
        model.addAttribute("questionTypes", QuestionType.values());
        model.addAttribute("question",questionManageService.getOneQuestion(questionId));
        return "question/edit-question-modal";
    }

    /**
     * 编辑一个习题
     * @param question
     * @return
     */
    @PostMapping("/edit")
    public String editQuestion(Question question){
        questionManageService.editOneQuestion(question);
        return "redirect:/admin/question/list";
    }

    /**
     * 删除习题
     * @param questionId
     * @return
     */
    @GetMapping("/delete/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") Long questionId){
        questionManageService.deleteOneQuestion(questionId);
        return "redirect:/admin/question/list";
    }
}
