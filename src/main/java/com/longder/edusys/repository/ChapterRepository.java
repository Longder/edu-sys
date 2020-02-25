package com.longder.edusys.repository;

import com.longder.edusys.entity.po.Chapter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository {

    /**
     * 插入新增一个新的班级
     * @param chapter
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO CHAPTER(subject_,name_,title_,content_,study_plan_) VALUES(#{subject},#{name},#{title},#{content},#{studyPlan})")
    void insert(Chapter chapter);
}
