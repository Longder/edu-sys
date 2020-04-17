package com.longder.edusys.service.impl;

import com.longder.edusys.entity.dto.ExamInitDto;
import com.longder.edusys.entity.dto.ExamResultDto;
import com.longder.edusys.entity.dto.ExamSubmitDto;
import com.longder.edusys.entity.enums.ChooseWay;
import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.*;
import com.longder.edusys.repository.*;
import com.longder.edusys.security.SecurityUtil;
import com.longder.edusys.service.ExamManageService;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    @Resource
    private ResultDetailRepository resultDetailRepository;
    @Resource
    private SysUserRepository sysUserRepository;

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
        examPaper.setCreateTime(LocalDateTime.now());
        GradeClass gradeClass = gradeClassRepository.getOne(examInitDto.getClassId());
        examPaper.setName(examInitDto.getName());
        examPaper.setGradeClass(gradeClass);
        List<Question> questionList = questionManageService.randomAllQuestion();
        //全随机题目
        //先存试卷
        examPaperRepository.insert(examPaper);
        //生成detail
        List<PaperDetail> detailList = new ArrayList<>();
        questionList.forEach(question -> detailList.add(new PaperDetail(examPaper, question)));
        //存detail
        paperDetailRepository.insert(detailList);
        //封装返回
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
        //判题，构建考试结果，计分
        AtomicReference<BigDecimal> score = new AtomicReference<>(BigDecimal.ZERO);
        examSubmitDto.getDetailList().forEach(resultDetail -> {
            Question question = questionManageService.getOneQuestion(resultDetail.getQuestionId());
            resultDetail.checkAnswer(question);
            resultDetail.setExamPaperId(examSubmitDto.getExamId());
            resultDetail.setExamResultId(result.getId());
            resultDetail.setStudentId(student.getId());
            if (resultDetail.getCorrect()) {
                score.updateAndGet(s -> s.add(question.getScore()));
            }
        });
        //存储
        resultDetailRepository.insert(examSubmitDto.getDetailList());
        //计分
        result.setScore(score.get());
        examResultRepository.updateScore(result);
    }

    /**
     * 查看某学生的考试结果列表
     * 区分自测考试还是正常考试
     *
     * @param studentId
     * @return
     */
    @Override
    public List<ExamResult> listExamResultForStudent(Long studentId) {
        List<ExamResult> resultList = examResultRepository.listByStudentId(studentId);
        //处理时间展示
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        resultList.forEach(examResult -> examResult.setCompleteTimeStr(formatter.format(examResult.getCompleteTime())));
        return resultList;
    }

    /**
     * 获取考试详情
     *
     * @param examResultId
     * @return
     */
    @Override
    public ExamResultDto getExamResultDetail(Long examResultId) {
        ExamResultDto dto = new ExamResultDto();
        ExamResult examResult = examResultRepository.getOne(examResultId);
        dto.setExamName(examResult.getExamPaperName());
        dto.setScore(examResult.getScore());
        //查询详情
        List<ResultDetail> detailList = resultDetailRepository.listByExamResultId(examResultId);
        dto.setDetailList(detailList);
        return dto;
    }

    /**
     * 查看某班级的考试班级考试列表
     *
     * @param gradeClassId
     * @return
     */
    @Override
    public List<ExamPaper> listClassExamForManage(Long gradeClassId) {
        List<ExamPaper> paperList = examPaperRepository.listNormalExamPaperByClassId(gradeClassId);
        paperList.forEach(examPaper -> {
            //处理创建时间展示
            examPaper.generateCreateTime();
            //统计参考学生人数
            Integer count = examResultRepository.countStudentForByExamPaperId(examPaper.getId());
            examPaper.setStudentCount(count==null?0:count);
        });
        return paperList;
    }

    /**
     * 查看某学生可以参加的班级考试列表
     *
     * @param gradeClassId
     * @param studentId
     * @return
     */
    @Override
    public List<ExamPaper> listClassExamForStudent(Long gradeClassId, Long studentId) {
        List<ExamPaper> paperList = examPaperRepository.listNormalExamPaperByClassId(gradeClassId);
        //过滤，检查改学生是否已经参加过此次考试，如果参加过就remove
        paperList.removeIf(examPaper -> {
            int count = examResultRepository.countByStudentIdAndExamPaperId(studentId,examPaper.getId());
            return count>0;
        });
        //处理创建时间展示
        paperList.forEach(ExamPaper::generateCreateTime);
        return paperList;
    }

    /**
     * 查询获取一个试卷信息，包括详情
     *
     * @param examPaperId
     * @return
     */
    @Override
    public ExamPaper getOneExamPaper(Long examPaperId) {
        ExamPaper examPaper = examPaperRepository.getOne(examPaperId);
        //试卷详情
        List<PaperDetail> paperDetailList = paperDetailRepository.listByExamPaperId(examPaperId);
        //封装
        examPaper.setDetailList(paperDetailList);
        return examPaper;
    }

    /**
     * 根据试卷查询此试卷的考试结果，包括所有学生的
     *
     * @param examPaperId
     * @return
     */
    @Override
    public List<ExamResult> listExamResultByPaperId(Long examPaperId) {
        List<ExamResult> resultList = examResultRepository.listByExamPaperId(examPaperId);
        //处理时间展示和学生姓名
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        resultList.forEach(examResult -> {
            examResult.setCompleteTimeStr(formatter.format(examResult.getCompleteTime()));
            SysUser student = sysUserRepository.getOne(examResult.getStudentId());
            examResult.setStudentName(student.getName());
        });

        return resultList;
    }
}
