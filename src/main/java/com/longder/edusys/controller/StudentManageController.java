package com.longder.edusys.controller;

import com.longder.edusys.entity.po.SysUser;
import com.longder.edusys.service.StudentManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/student")
public class StudentManageController {

    @Resource
    private StudentManageService studentManageService;

    /**
     * 学生列表页
     * @return
     */
    @GetMapping("/list")
    public String toStudentList(Model model){
        List<SysUser> studentList = studentManageService.listAllStudent();
        model.addAttribute("studentList",studentList);
        return "student/list";
    }

    /**
     * 去编辑学生页面
     * @param studentId
     * @return
     */
    @GetMapping("/toEdit")
    public String toEditStudent(Long studentId,Model model){
        SysUser student = studentManageService.getOneStudent(studentId);
        model.addAttribute("student",student);
        return "student/edit-student-modal";
    }

    @PostMapping("/edit")
    public String editStudent(SysUser student){
        studentManageService.editStudent(student);
        return "redirect:/admin/student/list";
    }
}
