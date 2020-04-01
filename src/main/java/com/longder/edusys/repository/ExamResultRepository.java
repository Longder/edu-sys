package com.longder.edusys.repository;

import com.longder.edusys.entity.enums.ExamType;
import com.longder.edusys.entity.po.ExamResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 考试结果表操作
 */
public interface ExamResultRepository {

    /**
     * 插入一条考试结果
     * @param examResult
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO EXAM_RESULT(exam_paper_id_, score_, complete_time_, student_id_) " +
            "values(#{examPaperId},#{score},#{completeTime},#{studentId})")
    void inset(ExamResult examResult);

    /**
     * 更新考试分数
     * @param examResult
     */
    @Update("update EXAM_RESULT set score_ = #{score} where id_ = #{id}")
    void updateScore(ExamResult examResult);

    /**
     * 根据学生Id和考试类型查询
     * @param studentId
     * @param examType
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ExamResultRepository.ExamResultResultMap")
    @Select("SELECT ER.id_,ER.exam_paper_id_, ER.student_id_,ER.score_,ER.complete_time_,EP.name_ as examPaperName  FROM EXAM_RESULT ER " +
            "    LEFT JOIN EXAM_PAPER EP on ER.exam_paper_id_ = EP.id_" +
            "    WHERE ER.student_id_ = #{studentId} AND EP.exam_type_ = #{examType.name}")
    List<ExamResult> listByStudentIdAndExamType(@Param("studentId") Long studentId, @Param("examType")ExamType examType);

    /**
     * 获取一个考试结果
     * @param examResultId
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ExamResultRepository.ExamResultResultMap")
    @Select("SELECT * FROM exam_result WHERE id_ = #{examResultId}")
    ExamResult getOne(@Param("examResultId") Long examResultId);

    /**
     * 根据试卷统计参考了此试卷的学生数量
     * @param examPaperId
     * @return
     */
    @Select("SELECT count(student_id_) as student_count " +
            "    FROM EXAM_RESULT where exam_paper_id_ = #{examPaperId}")
    Integer countStudentForByExamPaperId(@Param("examPaperId") Long examPaperId);

    /**
     * 根据学生和试卷id统计考试结果
     * @param studentId
     * @param examPaperId
     * @return
     */
    @Select("SELECT COUNT(id_) FROM EXAM_RESULT where student_id_ = #{studentId} and exam_paper_id_ = #{examPaperId}")
    Integer countByStudentIdAndExamPaperId(@Param("studentId") Long studentId,@Param("examPaperId")Long examPaperId);

    /**
     * 根据试卷id查询考试结果
     * @param examPaperId
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ExamResultRepository.ExamResultResultMap")
    @Select("SELECT ER.id_,ER.exam_paper_id_, ER.student_id_,ER.score_,ER.complete_time_,EP.name_ as examPaperName  FROM EXAM_RESULT ER " +
            "    LEFT JOIN EXAM_PAPER EP on ER.exam_paper_id_ = EP.id_" +
            "    WHERE EP.id_ = #{examPaperId}")
    List<ExamResult> listByExamPaperId(@Param("examPaperId")Long examPaperId);

    /**
     * 根据学生id删除考试结果
     * @param studentId
     */
    @Delete("DELETE FROM EXAM_RESULT WHERE student_id_ = #{studentId}")
    void deleteByStudentId(@Param("studentId") Long studentId);
}
