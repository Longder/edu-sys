package com.longder.edusys.service;

import com.longder.edusys.entity.dto.ExamInitDto;
import com.longder.edusys.entity.dto.ExamSubmitDto;
import com.longder.edusys.entity.po.ExamPaper;

/**
 * 考试管理相关service
 */
public interface ExamManageService {

    /**
     * 生成考试试卷
     * @param examInitDto
     * @return
     */
    ExamPaper generateExam(ExamInitDto examInitDto);

    /**
     * 完成提交考试
     * @param examSubmitDto
     */
    void completeExam(ExamSubmitDto examSubmitDto);
}
