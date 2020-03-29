package com.longder.edusys.controller;

import com.longder.edusys.entity.po.GradeClass;
import com.longder.edusys.service.ClassManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级管理的控制器
 */
@Controller
@RequestMapping("/admin/class")
public class ClassManageController {

    @Resource
    private ClassManageService classManageService;

    /**
     * 班级列表
     *
     * @return
     */
    @GetMapping("/list")
    public String toClassList(Model model) {
        List<GradeClass> classList = classManageService.listAllClasses();
        model.addAttribute("list", classList);
        return "class/list";
    }

    /**
     * 添加班级的modal页
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddClass() {
        return "class/add-class-modal";
    }

    /**
     * 添加班级
     *
     * @return
     */
    @PostMapping("/add")
    public String addClass(GradeClass gradeClass) {
        classManageService.addClass(gradeClass);
        return "redirect:/admin/class/list";
    }

    /**
     * 去编辑班级
     * @param classId
     * @param model
     * @return
     */
    @GetMapping("/toEdit")
    public String toEditClass(Long classId,Model model){
        model.addAttribute("gradeClass",classManageService.getOneClass(classId));
        return "class/edit-class-modal";
    }

    @PostMapping("/edit")
    public String editClass(GradeClass gradeClass){
        classManageService.editOneClass(gradeClass);
        return "redirect:/admin/class/list";
    }
}
