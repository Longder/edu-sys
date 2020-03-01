package com.longder.edusys.repository;

import com.longder.edusys.entity.po.GradeClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 班级表Dao
 */
@Repository
public interface GradeClassRepository {

    /**
     * 获取一个班级
     * @param id
     * @return
     */
    @ResultMap("com.longder.edusys.repository.GradeClassRepository.GradeClassResultMap")
    @Select("SELECT * FROM grade_class WHERE id_=#{id}")
    GradeClass getOne(Long id);

    /**
     * 新增插入一个班级
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO GRADE_CLASS(name_,description_) values(#{name},#{description})")
    void insert(GradeClass gradeClass);


    /**
     * 查询班级列表
     * @return
     */
    @ResultMap("com.longder.edusys.repository.GradeClassRepository.GradeClassResultMap")
    @Select("SELECT * FROM grade_class")
    List<GradeClass> listAll();
}
