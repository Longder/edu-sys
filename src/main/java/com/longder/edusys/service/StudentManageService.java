package com.longder.edusys.service;

import com.longder.edusys.entity.po.SysUser;

import java.util.List;

/**
 * 学生管理的业务
 */
public interface StudentManageService {

    /**
     * 查询所有学生
     * @return
     */
    List<SysUser> listAllStudent();

    /**
     * 查询获取一个学生信息
     * @param studentId
     * @return
     */
    SysUser getOneStudent(Long studentId);

    /**
     * 编辑更新学生信息
     * @param student
     */
    void editStudent(SysUser student);
}
