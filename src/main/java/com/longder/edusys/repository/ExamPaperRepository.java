package com.longder.edusys.repository;

import com.longder.edusys.entity.po.ExamPaper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * 考试表Dao
 */
@Repository
public interface ExamPaperRepository {

    @ResultMap("com.longder.edusys.repository.ExamPaperRepository.ExamPaperResultMap")
    @Select("SELECT * FROM exam_paper e,grade_class g WHERE e.grade_class_id_ = g.id_ and e.id_=#{id}")
    ExamPaper getOne(Long id);

    /**
     * 新插入一个试卷
     * @param examPaper
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO exam_paper(name_,grade_class_id_,exam_type_,student_id_) VALUES(#{name},#{gradeClass.id},#{examType},#{student.id})")
    void insert(ExamPaper examPaper);

}
