package com.longder.edusys.service;

import com.longder.edusys.entity.dto.ExamInitDto;
import com.longder.edusys.entity.po.Exam;

/**
 * 考试管理相关service
 */
public interface ExamManageService {

    /**
     * 生成考试
     * @param examInitDto
     * @return
     */
    Exam generateExam(ExamInitDto examInitDto);
}
