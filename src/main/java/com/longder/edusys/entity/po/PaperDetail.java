package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 试卷详情
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PaperDetail extends BaseIdEntity{

    /**
     * 试卷id
     */
    private Long examPaperId;

    /**
     * 题目
     */
    private Question question;

    public PaperDetail(ExamPaper examPaper,Question question){
        this.examPaperId = examPaper.getId();
        this.question = question;
    }

}
