package com.longder.edusys.repository;

import com.longder.edusys.BaseTest;
import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.ExamResult;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class ExamResultRepositoryTest extends BaseTest {
    @Resource
    private ExamResultRepository examResultRepository;

    @Test
    public void testList(){
        List<ExamResult> resultList = examResultRepository.listByStudentIdAndExamType(3L, ExamType.SELF);
        System.out.println(resultList.size());
    }
}
