package com.longder.edusys.controller;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.entity.po.Question;
import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.entity.vo.QuestionWrongCountListVo;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.ChapterManageService;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 复习功能控制器
 */
@Controller
@RequestMapping("/admin/reviewLesson")
public class ReviewLessonController {
    @Resource
    private QuestionManageService questionManageService;
    @Resource
    private ChapterManageService chapterManageService;
    /**
     * 错题列表
     * @return
     */
    @GetMapping("/wrongQuestion/list")
    public String wrongQuestionList(Model model){
        SysUser student = SecurityUtil.getCurrentUser();
        assert student != null;
        List<QuestionWrongCountListVo> voList = questionManageService.listWrongQuestionCount(student.getId());
        model.addAttribute("voList",voList);
        return "reviewLesson/wrong-question-list";
    }

    /**
     * 题目详情
     * @return
     */
    @GetMapping("/question/detail")
    public String questionDetail(Long questionId,Model model){
        Question question = questionManageService.getOneQuestion(questionId);
        model.addAttribute("question",question);
        return "reviewLesson/question-detail-modal";
    }

    /**
     * 章节详情
     * @param chapterId
     * @return
     */
    @GetMapping("/chapter/detail")
    public String chapterDetail(Long chapterId,Model model){
        Chapter chapter = chapterManageService.getOneChapter(chapterId);
        model.addAttribute("chapter",chapter);
        return "reviewLesson/chapter-detail-modal";
    }
}
