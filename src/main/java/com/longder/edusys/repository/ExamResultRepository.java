package com.longder.edusys.repository;

import com.longder.edusys.entity.po.ExamResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface ExamResultRepository {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO EXAM_RESULT(exam_paper_id_, score_, complete_time_, student_id_) " +
            "values(#{examPaperId},#{score},#{completeTime},#{studentId})")
    void inset(ExamResult examResult);
}
