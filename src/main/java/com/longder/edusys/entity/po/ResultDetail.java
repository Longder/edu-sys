package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 结果详情
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResultDetail extends BaseIdEntity{

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 考试结果id
     */
    private Long examResultId;

    /**
     * 试卷id
     */
    private Long examPaperId;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 题目，展示用
     */
    private Question question;

    /**
     * 填写的回答
     */
    private String answer;

    /**
     * 是否正确
     */
    private Boolean correct;

    /**
     * 判题
     */
    public void checkAnswer(Question question){
        if(question.getAnswer().equals(answer)){
            this.correct = true;
        }else{
            this.correct = false;
        }
    }

}

