package com.longder.edusys.repository;

import com.longder.edusys.entity.dto.QuestionCountDto;
import com.longder.edusys.entity.po.ResultDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDetailRepository {

    /**
     * 批量插入考试结果详情
     * @param detailList
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({
            "<script>",
            "insert into RESULT_DETAIL(student_id_, exam_result_id_, exam_paper_id_, question_id_, answer_, correct_) values ",
            "<foreach collection='detailList' item='item' index='index' separator=','>",
            "(#{item.studentId}, #{item.examResultId},#{item.examPaperId},#{item.questionId},#{item.answer},#{item.correct})",
            "</foreach>",
            "</script>"
    })
    void insert(@Param("detailList") List<ResultDetail> detailList);

    /**
     * 查询某个考试结果的详情
     * @param examResultId
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ResultDetailRepository.ResultDetailResultMap")
    @Select("SELECT RD.id_ AS rd_id, student_id_, exam_result_id_, exam_paper_id_, question_id_, RD.answer_ as rd_answer, correct_, " +
            "       Q.id_ AS q_id, content_, type_, score_, Q.answer_ as q_answer, choice_A_, choice_B_, choice_C_, choice_D_ " +
            "       FROM RESULT_DETAIL RD" +
            "    LEFT JOIN question q on rd.question_id_ = q.id_ WHERE RD.exam_result_id_ = #{examResultId}")
    List<ResultDetail> listByExamResultId(@Param("examResultId") Long examResultId);

    /**
     * 统计错题
     * @param studentId
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ResultDetailRepository.QuestionCountResultMap")
    @Select("SELECT question_id_,count(id_) as count_ FROM result_detail WHERE student_id_ = #{studentId} AND correct_ = 0" +
            "    GROUP BY question_id_")
    List<QuestionCountDto> countWrongQuestionByStudentId(@Param("studentId") Long studentId);

    /**
     * 根据班级id删除
     * @param classId
     */
    @Delete("DELETE FROM RESULT_DETAIL WHERE exam_paper_id_ IN (SELECT id_ FROM EXAM_PAPER WHERE grade_class_id_ = #{classId})")
    void deleteByGradeClassId(@Param("classId") Long classId);
}
