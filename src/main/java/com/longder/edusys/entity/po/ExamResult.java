package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
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
    private BigDecimal score;

    /**
     * 完成考试的时间
     */
    private LocalDateTime completeTime;

    /**
     * 完成时间字符串，展示用
     */
    private String completeTimeStr;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 试卷名称，不持久化，查询封装用
     */
    private String examPaperName;

    /**
     * 参考学生姓名，展示用
     */
    private String studentName;
}
