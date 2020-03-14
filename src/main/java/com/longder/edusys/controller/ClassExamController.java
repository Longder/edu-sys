package com.longder.edusys.controller;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.ChapterManageService;
import com.longder.edusys.service.ClassManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    private ClassManageService classManageService;

    /**
     * 定制选择考试
     *
     * @return
     */
    @GetMapping("/init")
    public String init(Model model) {
        List<Chapter> chapterList = chapterManageService.listAllChapter();
        //当前教师用户
        SysUser teacher = SecurityUtil.getCurrentUser();
        model.addAttribute("chapterList", chapterList);
        assert teacher != null;
        model.addAttribute("gradeClass", teacher.getGradeClass());
        return "classExam/init";
    }

    /**
     * 班级考试列表
     *
     * @return
     */
    @GetMapping("/list")
    public String listExam() {
        return "classExam/list";
    }

}
