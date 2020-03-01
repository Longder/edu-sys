package com.longder.edusys.service;

import com.longder.edusys.entity.po.Chapter;

import java.util.List;

/**
 * 学科章节管理业务的service
 */
public interface ChapterManageService {

    /**
     * 查询所有学科章节
     * @return
     */
    List<Chapter> listAllChapter();

    /**
     * 添加新增一个学科章节
     * @param chapter
     */
    void addOneChapter(Chapter chapter);

    /**
     * 查询获取一个章节信息
     * @param chapterId
     * @return
     */
    Chapter getOneChapter(Long chapterId);
}
