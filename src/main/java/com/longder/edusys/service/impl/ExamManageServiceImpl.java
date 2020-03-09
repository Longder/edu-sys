package com.longder.edusys.service.impl;

import com.longder.edusys.entity.dto.ExamInitDto;
import com.longder.edusys.entity.dto.ExamSubmitDto;
import com.longder.edusys.entity.enums.ChooseWay;
import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.*;
import com.longder.edusys.repository.ExamPaperRepository;
import com.longder.edusys.repository.ExamResultRepository;
import com.longder.edusys.repository.GradeClassRepository;
import com.longder.edusys.repository.PaperDetailRepository;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.ExamManageService;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试管理相关service
 */
@Service
public class ExamManageServiceImpl implements ExamManageService {

    @Resource
    private QuestionManageService questionManageService;
    @Resource
    private GradeClassRepository gradeClassRepository;
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private PaperDetailRepository paperDetailRepository;
    @Resource
    private ExamResultRepository examResultRepository;

    /**
     * 生成考试
     *
     * @param examInitDto
     * @return
     */
    @Override
    @Transactional
    public ExamPaper generateExam(ExamInitDto examInitDto) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setExamType(examInitDto.getExamType());
        //判断考试类型
        if (examInitDto.getExamType() == ExamType.SELF) {
            examPaper.setName("自我测验");
            //自我测验要记录当前用户的id和所属班级id
            SysUser student = SecurityUtil.getCurrentUser();
            examPaper.setStudent(student);
            assert student != null;
            examPaper.setGradeClass(student.getGradeClass());
        } else if (examInitDto.getExamType() == ExamType.NORMAL) {
            GradeClass gradeClass = gradeClassRepository.getOne(examInitDto.getClassId());
            examPaper.setGradeClass(gradeClass);
        }

        List<Question> questionList = new ArrayList<>();
        //判断选题方式，全随机还是指定章节
        if (examInitDto.getChooseWay() == ChooseWay.ALL) {
            questionList = questionManageService.randomAllQuestion();
        } else if (examInitDto.getChooseWay() == ChooseWay.ASSIGN) {
            questionList = questionManageService.randomAssignQuestion(examInitDto.getChapterIds());
        }
        //先存试卷
        examPaperRepository.insert(examPaper);
        //生成detail
        List<PaperDetail> detailList = new ArrayList<>();
        questionList.forEach(question -> detailList.add(new PaperDetail(examPaper, question)));
        //存detail
        paperDetailRepository.insert(detailList);
        examPaper.setDetailList(detailList);
        return examPaper;
    }

    /**
     * 完成提交考试
     *
     * @param examSubmitDto
     */
    @Override
    @Transactional
    public void completeExam(ExamSubmitDto examSubmitDto) {
        System.out.println(examSubmitDto);
        //生成考试结果，先存储
        ExamResult result = new ExamResult();
        result.setExamPaperId(examSubmitDto.getExamId());
        SysUser student = SecurityUtil.getCurrentUser();
        assert student != null;
        result.setStudentId(student.getId());
        result.setCompleteTime(LocalDateTime.now());
        examResultRepository.inset(result);
        //判题
        examSubmitDto.getDetailList().forEach(resultDetail -> {
            Question question = questionManageService.getOneQuestion(resultDetail.getQuestionId());
            resultDetail.checkAnswer(question);
            resultDetail.setExamPaperId(examSubmitDto.getExamId());
            resultDetail.setExamResultId(result.getId());
            resultDetail.setStudentId(student.getId());
        });
        //存储


    }
}
