package com.longder.edusys.controller;

import com.longder.edusys.entity.dto.ExamResultDto;
import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.entity.po.ExamResult;
import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.entity.vo.QuestionWrongCountListVo;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.ChapterManageService;
import com.longder.edusys.service.ExamManageService;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Resource
    private ExamManageService examManageService;

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

    /**
     * 自我测验结果页面
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        SysUser student = SecurityUtil.getCurrentUser();
        assert student != null;
        List<ExamResult> resultList = examManageService.listExamResultForStudent(student.getId(), ExamType.SELF);
        model.addAttribute("resultList",resultList);
        return "selfExam/list";
    }

    /**
     * 自我测验考试结果详情
     * @param model
     * @param examResultId
     * @return
     */
    @GetMapping("/detail/{examResultId}")
    public String detail(Model model,@PathVariable("examResultId") Long examResultId){
        ExamResultDto dto = examManageService.getExamResultDetail(examResultId);
        model.addAttribute("dto",dto);
        return "selfExam/detail";
    }


}
