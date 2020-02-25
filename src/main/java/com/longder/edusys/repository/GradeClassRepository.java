package com.longder.edusys.repository;

import com.longder.edusys.entity.po.GradeClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeClassRepository {

    /**
     * 新增插入一个班级
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO GRADE_CLASS(name_,description_) values(#{name},#{description})")
    void insert(GradeClass gradeClass);
}
