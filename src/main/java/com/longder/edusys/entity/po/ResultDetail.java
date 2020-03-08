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
     * 考试详情id
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
     * 填写的回答
     */
    private String answer;

    /**
     * 是否正确
     */
    private Boolean correct;

}

