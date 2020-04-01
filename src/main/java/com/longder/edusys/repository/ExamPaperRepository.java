package com.longder.edusys.repository;

import com.longder.edusys.entity.po.ExamPaper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    @Insert("INSERT INTO exam_paper(nam«e_,grade_class_id_,exam_type_,student_id_,create_time_) VALUES(#{name},#{gradeClass.id},#{examType},#{student.id},#{createTime})")
    void insert(ExamPaper examPaper);

    /**
     * 查询某班级下的正式考试列表
     * @param gradeClassId
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ExamPaperRepository.ExamPaperResultMap")
    @Select("SELECT e.*,g.*,g.name_ as class_name FROM exam_paper e,grade_class g WHERE e.grade_class_id_ = g.id_ and e.grade_class_id_ = #{gradeClassId} and e.exam_type_ = 'NORMAL'")
    List<ExamPaper> listNormalExamPaperByClassId(@Param("gradeClassId") Long gradeClassId);

    /**
     * 根据学生Id删除试卷（自测的）
     * @param studentId
     */
    @Delete("DELETE FROM exam_paper WHERE student_id_ = #{studentId}")
    void deleteByStudentId(@Param("studentId") Long studentId);
}
