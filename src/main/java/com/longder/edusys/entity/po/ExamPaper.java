package com.longder.edusys.entity.po;

import com.longder.edusys.entity.enums.ExamType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 考试试卷
 * 自测和正式考试都会先生成试卷
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExamPaper extends BaseIdEntity{

    /**
     * 自测的考试没有名称
     */
    private String name;

    /**
     * 关联班级(一定有)
     */
    private GradeClass gradeClass;

    /**
     * 关联学生（自测才有）
     */
    private SysUser student;

    /**
     * 考试类型
     */
    private ExamType examType;

    /**
     * 试卷详情，展示用
     */
    private List<PaperDetail> detailList;
}
