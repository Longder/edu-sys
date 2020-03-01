package com.longder.edusys.repository;

import com.longder.edusys.BaseTest;
import com.longder.edusys.entity.po.Exam;
import com.longder.edusys.entity.po.GradeClass;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class ExamRepositoryTest extends BaseTest {

    @Resource
    private GradeClassRepository gradeClassRepository;
    @Resource
    private ExamRepository examRepository;

    @Test
    public void testInsert(){
        Exam exam = new Exam();
        exam.setName("考试一");
        exam.setHours(1);
        exam.setMinutes(30);
        GradeClass gradeClass = gradeClassRepository.getOne(1L);
        exam.setGradeClass(gradeClass);
        examRepository.insert(exam);
    }

    @Test
    public void testGetOne(){
        Exam exam = examRepository.getOne(1L);
        System.out.println(exam);
    }
}
