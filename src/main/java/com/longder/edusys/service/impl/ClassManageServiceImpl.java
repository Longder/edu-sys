package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.GradeClass;
import com.longder.edusys.repository.GradeClassRepository;
import com.longder.edusys.service.ClassManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassManageServiceImpl implements ClassManageService {

    @Resource
    private GradeClassRepository gradeClassRepository;

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
}
