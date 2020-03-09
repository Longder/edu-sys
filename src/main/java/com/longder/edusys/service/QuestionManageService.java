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

    /**
     * 全章节随机选题，保证满分
     * @return
     */
    List<Question> randomAllQuestion();

    /**
     * 在指定章节中选题
     * @param chapterIds
     * @return
     */
    List<Question> randomAssignQuestion(List<Long> chapterIds);

    /**
     * 查询获取一个题目
     * @param questionId
     * @return
     */
    Question getOneQuestion(Long questionId);
}
