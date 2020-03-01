package com.longder.edusys.service;

import com.longder.edusys.entity.po.Question;

import java.util.List;

/**
 * 习题管理的业务
 */

public interface QuestionManageService {

    /**
     * 添加一个习题
     * @param question
     */
    void addOneQuestion(Question question);

    /**
     * 查询所有习题
     * @return
     */
    List<Question> listAllQuestion();
}
