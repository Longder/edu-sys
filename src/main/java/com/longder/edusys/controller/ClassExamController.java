package com.longder.edusys.controller;

import com.longder.edusys.entity.dto.ExamResultDto;
import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.ExamPaper;
import com.longder.edusys.entity.po.ExamResult;
import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.ExamManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private ExamManageService examManageService;


    /**
     * 定制选择考试
     *
     * @return
     */
    @GetMapping("/init")
    public String init(Model model) {
        //当前教师用户
        SysUser teacher = SecurityUtil.getCurrentUser();
        assert teacher != null;
        model.addAttribute("gradeClass", teacher.getGradeClass());
        return "classExam/init";
    }

    /**
     * 班级考试管理列表
     * (教师用)
     *
     * @return
     */
    @GetMapping("/manage/list")
    public String manageListExam(Model model) {
        //当前用户的所属班级
        SysUser sysUser = SecurityUtil.getCurrentUser();
        assert sysUser != null;
        List<ExamPaper> paperList = examManageService.listClassExamForManage(sysUser.getGradeClass().getId());
        model.addAttribute("paperList", paperList);
        return "classExam/manage-list";
    }

    /**
     * 试卷详情
     *
     * @return
     */
    @GetMapping("/detail/{paperId}")
    public String detail(@PathVariable("paperId") Long paperId, Model model) {
        ExamPaper examPaper = examManageService.getOneExamPaper(paperId);
        model.addAttribute("examPaper", examPaper);
        return "classExam/detail";
    }

    /**
     * 班级考试列表
     * (学生用)
     *
     * @return
     */
    @GetMapping("/list")
    public String listExam(Model model) {
        SysUser student = SecurityUtil.getCurrentUser();
        assert student != null;
        List<ExamPaper> paperList = examManageService.listClassExamForStudent(student.getGradeClass().getId(), student.getId());
        model.addAttribute("paperList", paperList);
        return "classExam/list";
    }

    /**
     * 开始考试
     *
     * @param paperId
     * @return
     */
    @GetMapping("/startExam/{paperId}")
    public String startExam(@PathVariable("paperId") Long paperId, Model model) {
        ExamPaper examPaper = examManageService.getOneExamPaper(paperId);
        model.addAttribute("exam", examPaper);
        return "exam/exam-paper";
    }

    /**
     * 考试结果列表，学生用
     *
     * @return
     */
    @GetMapping("/result")
    public String resultList(Model model) {
        SysUser student = SecurityUtil.getCurrentUser();
        assert student != null;
        List<ExamResult> resultList = examManageService.listExamResultForStudent(student.getId());
        model.addAttribute("resultList", resultList);
        return "classExam/result-list";
    }

    /**
     * 教师查看某个考试的所有学生考试结果列表
     *
     * @return
     */
    @GetMapping("/resultForTeacher/{examPaperId}")
    public String resultListForTeacher(Model model, @PathVariable("examPaperId") Long examPaperId) {
        List<ExamResult> resultList = examManageService.listExamResultByPaperId(examPaperId);
        model.addAttribute("resultList", resultList);
        return "classExam/result-list-by-paper";
    }

    /**
     * 考试结果查看
     *
     * @param resultId
     * @return
     */
    @GetMapping("/resultDetail/{resultId}")
    public String resultDetail(@PathVariable("resultId") Long resultId, Model model) {
        ExamResultDto dto = examManageService.getExamResultDetail(resultId);
        model.addAttribute("dto", dto);
        return "classExam/result-detail";
    }
}
