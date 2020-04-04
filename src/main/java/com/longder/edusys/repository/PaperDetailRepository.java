package com.longder.edusys.repository;

import com.longder.edusys.entity.po.PaperDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperDetailRepository {

    /**
     * 批量插入考试详情
     * @param detailList
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({
            "<script>",
            "insert into PAPER_DETAIL(exam_paper_id_,question_id_) values ",
            "<foreach collection='detailList' item='item' index='index' separator=','>",
            "(#{item.examPaperId}, #{item.question.id})",
            "</foreach>",
            "</script>"
    })
    void insert(@Param("detailList") List<PaperDetail> detailList);

    /**
     * 根据试卷id查询试卷详情
     * @param examPaperId
     * @return
     */
    @ResultMap("com.longder.edusys.repository.PaperDetailRepository.PaperDetailResultMap")
    @Select("SELECT PD.id_ AS pd_id,  exam_paper_id_, question_id_,Q.* FROM PAPER_DETAIL PD " +
            "    LEFT JOIN QUESTION Q on PD.question_id_ = Q.id_ " +
            "    WHERE exam_paper_id_ = #{examPaperId}")
    List<PaperDetail> listByExamPaperId(@Param("examPaperId") Long examPaperId);

    /**
     * 根据班级id删除
     * @param classId
     */
    @Delete("DELETE FROM PAPER_DETAIL WHERE exam_paper_id_ IN (SELECT id_ FROM EXAM_PAPER WHERE grade_class_id_ = #{classId})")
    void deleteByGradeClassId(@Param("classId")Long classId);
}

