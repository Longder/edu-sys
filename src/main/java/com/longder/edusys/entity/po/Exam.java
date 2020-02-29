package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考试实体
 * 代表某一次考试
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Exam extends BaseIdEntity{
    /**
     * 名称
     */
    private String name;
    /**
     * 关联班级
     */
    private GradeClass gradeClass;
    /**
     * 小时数（考试时间）
     */
    private Integer hours;
    /**
     * 分钟数 (考试时间)
     */
    private Integer minutes;
}
