package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.repository.ChapterRepository;
import com.longder.edusys.service.ChapterManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学科章节管理业务的service
 */
@Service
public class ChapterManageServiceImpl implements ChapterManageService {

    @Resource
    private ChapterRepository chapterRepository;

    /**
     * 查询所有学科章节
     *
     * @return
     */
    @Override
    public List<Chapter> listAllChapter() {
        List<Chapter> chapterList = chapterRepository.listAll();
        chapterList.forEach(chapter -> chapter.setSubjectTitle(chapter.getSubject()+"-"+chapter.getTitle()));
        return chapterList;
    }

    /**
     * 添加新增一个学科章节
     *
     * @param chapter
     */
    @Override
    @Transactional
    public void addOneChapter(Chapter chapter) {
        chapterRepository.insert(chapter);
    }

    /**
     * 查询获取一个章节信息
     *
     * @param chapterId
     * @return
     */
    @Override
    public Chapter getOneChapter(Long chapterId) {
        Chapter chapter = chapterRepository.getOne(chapterId);
        chapter.generateSubjectTitle();
        return chapter;
    }
}
