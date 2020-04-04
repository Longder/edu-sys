package com.longder.edusys.service;

import com.longder.edusys.entity.po.GradeClass;

import java.util.List;

/**
 * 班级管理相关业务
 */
public interface ClassManageService {

    /**
     * 查询所有班级
     * @return
     */
    List<GradeClass> listAllClasses();

    /**
     * 验证班级名称
     * @param className
     * @return true: 验证通过  false: 验证不通过
     */
    boolean validClassName(String className);

    /**
     * 添加班级
     * @param gradeClass
     */
    void addClass(GradeClass gradeClass);

    /**
     * 获取一个班级信息
     * @param classId
     * @return
     */
    GradeClass getOneClass(Long classId);

    /**
     * 编辑一个班级
     * @param gradeClass
     */
    void editOneClass(GradeClass gradeClass);

    /**
     * 删除一个班级以及所有关联信息
     * @param classId
     */
    void deleteOneClass(Long classId);
}
