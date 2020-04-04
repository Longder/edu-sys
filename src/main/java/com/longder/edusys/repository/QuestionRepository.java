package com.longder.edusys.repository;

import com.longder.edusys.entity.po.Question;
import org.apache.ibatis.annotations.*;
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

    @Update("UPDATE QUESTION SET content_ = #{content},type_ = #{type.name},answer_ = #{answer},choice_A_ = #{choiceA},choice_B_=#{choiceB},choice_C_=#{choiceC},choice_D_=#{choiceD} " +
            " WHERE id_ = #{id}")
    void update(Question question);

    /**
     * 查询所有题目
     * @return
     */
    @ResultMap("com.longder.edusys.repository.QuestionRepository.QuestionResultMap")
    @Select("SELECT Q.id_ AS q_id,Q.content_ as q_content,Q.*,C.id_ as c_id,c.content_ as c_content,c.* " +
            "    FROM QUESTION Q LEFT JOIN CHAPTER C on Q.chapter_id_ = C.id_")
    List<Question> listAll();

    /**
     * 查询指定某几个章节下的题目
     * @param chapterIds
     * @return
     */
    @ResultMap("com.longder.edusys.repository.QuestionRepository.QuestionResultMap")
    @Select({
            "<script>",
            "SELECT Q.id_ AS q_id,Q.content_ as q_content,Q.*,C.id_ as c_id,c.content_ as c_content,c.* FROM QUESTION Q LEFT JOIN CHAPTER C on Q.chapter_id_ = C.id_",
            "WHERE Q.chapter_id_ in ",
            "<foreach collection='chapterIds' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Question> listByChapterIds(@Param("chapterIds")List<Long> chapterIds);

    @ResultMap("com.longder.edusys.repository.QuestionRepository.QuestionResultMap")
    @Select("SELECT Q.id_ AS q_id,Q.content_ as q_content,Q.*,C.id_ as c_id,c.content_ as c_content,c.* " +
            "    FROM QUESTION Q LEFT JOIN CHAPTER C on Q.chapter_id_ = C.id_ WHERE Q.id_ = #{questionId}")
    Question getOne(Long questionId);

    /**
     * 根据章节id删除
     * @param chapterId
     */
    @Delete("DELETE FROM QUESTION WHERE chapter_id_ = #{chapterId}")
    void deleteByChapterId(@Param("chapterId") Long chapterId);

    /**
     * 根据id删除题目
     * @param id
     */
    @Delete("DELETE from QUESTION where id_ = #{id_}")
    void deleteById(@Param("id_") Long id);
}
