package com.longder.edusys.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 错题统计dto
 */
@Data
public class QuestionCountDto implements Serializable {
    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 错误次数
     */
    private Integer count;
}
