package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试详情，针对某个学生的某次考试的某一个题目的情况
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExamDetail extends BaseIdEntity{

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 班级id
     */
    private Long gradClassId;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 是否正确
     */
    private Boolean correct;

}
