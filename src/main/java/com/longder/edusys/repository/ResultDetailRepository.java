package com.longder.edusys.repository;

import com.longder.edusys.entity.po.ResultDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
}
