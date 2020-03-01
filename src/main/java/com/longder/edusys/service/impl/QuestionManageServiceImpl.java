package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.Question;
import com.longder.edusys.repository.QuestionRepository;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionManageServiceImpl implements QuestionManageService {

    @Resource
    private QuestionRepository questionRepository;

    /**
     * 添加一个习题
     *
     * @param question
     */
    @Override
    @Transactional
    public void addOneQuestion(Question question) {
        questionRepository.insert(question);
    }

    /**
     * 查询所有习题
     *
     * @return
     */
    @Override
    public List<Question> listAllQuestion() {
        List<Question> questionList = questionRepository.listAll();
        //处理习题关联的chapter展示问题
        questionList.forEach(question -> question.getChapter().generateSubjectTitle());
        return questionList;
    }
}
