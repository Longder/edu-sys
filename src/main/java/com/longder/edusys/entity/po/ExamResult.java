package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 考试结果，每个学生参加的每次考试都存一份
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExamResult extends BaseIdEntity {
    /**
     * 试卷id
     */
    private Long examPaperId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 完成考试的时间
     */
    private LocalDateTime completeTime;

    /**
     * 学生id
     */
    private Long studentId;
}
