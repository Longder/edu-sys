package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.GradeClass;
import com.longder.edusys.repository.*;
import com.longder.edusys.service.ClassManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassManageServiceImpl implements ClassManageService {

    @Resource
    private GradeClassRepository gradeClassRepository;
    @Resource
    private ExamResultRepository examResultRepository;
    @Resource
    private ResultDetailRepository resultDetailRepository;
    @Resource
    private PaperDetailRepository paperDetailRepository;
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private SysUserRepository sysUserRepository;

    /**
     * 查询所有班级
     *
     * @return
     */
    @Override
    public List<GradeClass> listAllClasses() {
        return gradeClassRepository.listAll();
    }

    /**
     * 验证班级名称
     *
     * @param className
     * @return true: 验证通过  false: 验证不通过
     */
    @Override
    public boolean validClassName(String className) {
        return false;
    }

    /**
     * 添加班级
     *
     * @param gradeClass
     */
    @Override
    @Transactional
    public void addClass(GradeClass gradeClass) {
        gradeClassRepository.insert(gradeClass);
    }

    /**
     * 获取一个班级信息
     *
     * @param classId
     * @return
     */
    @Override
    public GradeClass getOneClass(Long classId) {
        return gradeClassRepository.getOne(classId);
    }

    /**
     * 编辑一个班级
     *
     * @param gradeClass
     */
    @Override
    @Transactional
    public void editOneClass(GradeClass gradeClass) {
        gradeClassRepository.update(gradeClass);
    }

    /**
     * 删除一个班级以及所有关联信息
     *
     * @param classId
     */
    @Override
    @Transactional
    public void deleteOneClass(Long classId) {
        //删除考试结果详情
        resultDetailRepository.deleteByGradeClassId(classId);
        //删除考试结果
        examResultRepository.deleteByGradeCLassId(classId);
        //删除试卷详情
        paperDetailRepository.deleteByGradeClassId(classId);
        //删除试卷
        examPaperRepository.deleteByGradeClassId(classId);
        //删除老师和学生
        sysUserRepository.deleteByClassId(classId);
        //删除班级
        gradeClassRepository.deleteById(classId);
    }
}
