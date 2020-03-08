package com.longder.edusys.repository;

import com.longder.edusys.entity.po.PaperDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
}
