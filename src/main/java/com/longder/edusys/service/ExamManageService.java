package com.longder.edusys.service;

import com.longder.edusys.entity.dto.ExamInitDto;
import com.longder.edusys.entity.dto.ExamResultDto;
import com.longder.edusys.entity.dto.ExamSubmitDto;
import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.ExamPaper;
import com.longder.edusys.entity.po.ExamResult;

import java.util.List;

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

    /**
     * 查看某学生的考试结果列表
     * 区分自测考试还是正常考试
     * @return
     */
    List<ExamResult> listExamResultForStudent(Long studentId, ExamType examType);

    /**
     * 获取考试详情
     * @param examResultId
     * @return
     */
    ExamResultDto getExamResultDetail(Long examResultId);

}
