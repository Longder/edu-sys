package com.longder.edusys.repository;

import com.longder.edusys.entity.po.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 习题表dao
 */
@Repository
public interface QuestionRepository {

    @Insert("INSERT INTO QUESTION(content_, type_, score_, answer_, chapter_id_, choice_A_, choice_B_, choice_C_, choice_D_) " +
            "VALUES(#{content},#{type.name},#{score},#{answer},#{chapterId},#{choiceA},#{choiceB},#{choiceC},#{choiceD})")
    void insert(Question question);

    /**
     * 查询所有题目
     * @return
     */
    @ResultMap("com.longder.edusys.repository.QuestionRepository.QuestionResultMap")
    @Select("SELECT Q.id_ AS q_id,Q.content_ as q_content,Q.*,C.id_ as c_id,c.content_ as c_content,c.* " +
            "    FROM QUESTION Q LEFT JOIN CHAPTER C on Q.chapter_id_ = C.id_")
    List<Question> listAll();
}