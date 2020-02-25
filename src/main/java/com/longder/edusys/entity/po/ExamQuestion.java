package com.longder.edusys.entity.po;

import lombok.Data;

/**
 * 考试题目
 * 考试和题目的关联
 */
@Data
public class ExamQuestion extends BaseIdEntity{
    /**
     * 考试id
     */
    private Long examId;
    /**
     * 题目id
     */
    private Long questionId;
}
