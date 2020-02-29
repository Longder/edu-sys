package com.longder.edusys.repository;

import com.longder.edusys.entity.po.Exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository {

    @ResultMap("com.longder.edusys.repository.ExamRepository.ExamResultMap")
    @Select("SELECT * FROM exam e,grade_class g WHERE e.grade_class_id_ = g.id_ and e.id_=#{id}")
    Exam getOne(Long id);

    /**
     * 新插入一个考试
     * @param exam
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO EXAM(name_,hours_,minutes_,grade_class_id_) VALUES(#{name},#{hours},#{minutes},#{gradeClass.id})")
    void insert(Exam exam);

}
