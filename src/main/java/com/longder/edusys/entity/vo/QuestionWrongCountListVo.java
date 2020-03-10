package com.longder.edusys.entity.vo;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.entity.po.Question;
import lombok.Data;

import java.io.Serializable;

/**
 * 错题统计列表展示vo
 */
@Data
public class QuestionWrongCountListVo implements Serializable {

    /**
     * 题目
     */
    private Question question;

    /**
     * 章节
     */
    private Chapter chapter;

    /**
     * 错误次数
     */
    private Integer count;
}
