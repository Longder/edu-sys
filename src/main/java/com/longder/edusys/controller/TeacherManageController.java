package com.longder.edusys.controller;

import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.service.TeacherManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师管理业务的控制器
 */
@Controller
@RequestMapping("/admin/teacher")
public class TeacherManageController {

    @Resource
    private TeacherManageService teacherManageService;


    /**
     * 教师列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String toTeacherList(Model model){
        List<SysUser> teacherList = teacherManageService.listAllTeacher();
        model.addAttribute("teacherList",teacherList);
        return "teacher/list";
    }

    /**
     * 去编辑教师页
     * @param model
     * @param teacherId
     * @return
     */
    @GetMapping("/toEdit")
    public String toEditTeacher(Model model,Long teacherId){
        model.addAttribute("teacher",teacherManageService.getOneTeacher(teacherId));
        return "teacher/edit-teacher-modal";
    }

    /**
     * 编辑教师
     * @param teacher
     * @return
     */
    @PostMapping("/edit")
    public String editTeacher(SysUser teacher){
        teacherManageService.editTeacher(teacher);
        return "redirect:/admin/teacher/list";
    }

    /**
     * 删除教师
     * @param teacherId
     * @return
     */
    @GetMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") Long teacherId){
        teacherManageService.deleteOneTeacher(teacherId);
        return "redirect:/admin/teacher/list";
    }


}
