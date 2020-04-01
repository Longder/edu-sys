package com.longder.edusys.service;

import com.longder.edusys.entity.po.SysUser;

import java.util.List;

/**
 * 教师管理相关业务
 */
public interface TeacherManageService {

    /**
     * 查询教师列表
     * @return
     */
    List<SysUser> listAllTeacher();

    /**
     * 查询获取一个教师信息
     * @param teacherId
     * @return
     */
    SysUser getOneTeacher(Long teacherId);

    /**
     * 编辑一个教师信息
     * @param teacher
     */
    void editTeacher(SysUser teacher);

    /**
     * 删除一个教师
     * @param teacherId
     */
    void deleteOneTeacher(Long teacherId);
}
