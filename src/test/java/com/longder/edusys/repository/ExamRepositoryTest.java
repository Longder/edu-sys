package com.longder.edusys.repository;

import com.longder.edusys.BaseTest;
import com.longder.edusys.entity.po.ExamPaper;
import com.longder.edusys.entity.po.GradeClass;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class ExamRepositoryTest extends BaseTest {

    @Resource
    private GradeClassRepository gradeClassRepository;
    @Resource
    private ExamPaperRepository examPaperRepository;

    @Test
    public void testInsert(){
        ExamPaper paper = new ExamPaper();
        paper.setName("考试一");
        GradeClass gradeClass = gradeClassRepository.getOne(1L);
        paper.setGradeClass(gradeClass);
        examPaperRepository.insert(paper);
    }

    @Test
    public void testGetOne(){
        ExamPaper exam = examPaperRepository.getOne(1L);
        System.out.println(exam);
    }
}
