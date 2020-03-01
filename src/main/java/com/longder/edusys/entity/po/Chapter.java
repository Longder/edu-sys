package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 章节
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Chapter extends BaseIdEntity{

    /**
     * 所属学科
     */
    private String subject;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 章节内容
     */
    private String content;

    /**
     * 学习计划
     */
    private String studyPlan;

    /**
     * 学科-章节标题，不持久化，展示用
     */
    private String subjectTitle;

    public void generateSubjectTitle(){
        this.subjectTitle = this.subject +"-"+this.title;
    }

}
