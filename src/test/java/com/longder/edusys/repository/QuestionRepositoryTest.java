package com.longder.edusys.repository;

import com.longder.edusys.BaseTest;

import com.longder.edusys.entity.po.Question;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryTest extends BaseTest {
    @Resource
    private QuestionRepository questionRepository;

    @Test
    public void testListAll(){
        List<Question> questionList = questionRepository.listAll();
        System.out.println(questionList.size());
    }

}
