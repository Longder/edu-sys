package com.longder.edusys.repository;

import com.longder.edusys.entity.po.Chapter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 章节表dao
 */
@Repository
public interface ChapterRepository {

    /**
     * 插入新增一个新的学科章节
     * @param chapter
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO CHAPTER(subject_,name_,title_,content_,study_plan_) VALUES(#{subject},#{name},#{title},#{content},#{studyPlan})")
    void insert(Chapter chapter);

    /**
     * 查询所有章节
     * @return
     */
    @ResultMap("com.longder.edusys.repository.ChapterRepository.ChapterResultMap")
    @Select("SELECT * FROM CHAPTER")
    List<Chapter> listAll();


    @ResultMap("com.longder.edusys.repository.ChapterRepository.ChapterResultMap")
    @Select("SELECT * FROM CHAPTER WHERE id_ = #{id}")
    Chapter getOne(Long id);

    @Delete("DELETE FROM CHAPTER WHERE id_ = #{id}")
    void deleteById(@Param("id") Long id);
}
