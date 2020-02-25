package com.longder.edusys.repository;


import com.longder.edusys.BaseTest;
import com.longder.edusys.entity.po.GradeClass;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class GradeClassRepositoryTest extends BaseTest {

    @Resource
    private GradeClassRepository gradeClassRepository;

    @Test
    public void testInsert(){
        GradeClass gradeClass = new GradeClass();
        gradeClass.setName("一班");
        gradeClass.setDescription("一班的描述");
        gradeClassRepository.insert(gradeClass);
    }
}
