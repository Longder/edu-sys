package com.longder.edusys.entity.dto;


import java.io.Serializable;

/**
 * 试卷和参考学生数的dto
 */
public class ExamPaperStudentCountDto implements Serializable {

    /**
     * 试卷id
     */
    private Long examPaperId;

    /**
     * 学生数量
     */
    private Integer studentCount;
}
