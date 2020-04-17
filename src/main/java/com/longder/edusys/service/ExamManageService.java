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
    List<ExamResult> listExamResultForStudent(Long studentId);

    /**
     * 获取考试详情
     * @param examResultId
     * @return
     */
    ExamResultDto getExamResultDetail(Long examResultId);

    /**
     * 查看某班级的考试班级考试列表（教师用）
     * @param gradeClassId
     * @return
     */
    List<ExamPaper> listClassExamForManage(Long gradeClassId);

    /**
     * 查看某学生可以参加的班级考试列表
     * @param gradeClassId
     * @param studentId
     * @return
     */
    List<ExamPaper> listClassExamForStudent(Long gradeClassId,Long studentId);

    /**
     * 查询获取一个试卷信息，包括详情
     * @param examPaperId
     * @return
     */
    ExamPaper getOneExamPaper(Long examPaperId);

    /**
     * 根据试卷查询此试卷的考试结果，包括所有学生的
     * @param examPaperId
     * @return
     */
    List<ExamResult> listExamResultByPaperId(Long examPaperId);

}
