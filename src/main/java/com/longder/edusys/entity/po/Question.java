package com.longder.edusys.entity.po;

import com.longder.edusys.entity.enums.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 题目实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Question extends BaseIdEntity{
    /**
     * 题目内容
     */
    public String content;

    /**
     * 题目类型
     */
    public QuestionType type;

    /**
     * 分数
     */
    private BigDecimal score;
    /**
     * 答案
     */
    private String answer;

    /**
     * 选项A内容（选择题才有）
     */
    private String choiceA;

    /**
     * 选项B内容（选择题才有）
     */
    private String choiceB;

    /**
     * 选项C内容（选择题才有）
     */
    private String choiceC;

    /**
     * 选项D内容（选择题才有）
     */
    private String choiceD;

}
