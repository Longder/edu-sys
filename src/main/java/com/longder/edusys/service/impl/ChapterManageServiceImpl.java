package com.longder.edusys.service.impl;

import com.longder.edusys.entity.po.Chapter;
import com.longder.edusys.repository.ChapterRepository;
import com.longder.edusys.repository.QuestionRepository;
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
    @Resource
    private QuestionRepository questionRepository;

    /**
     * 查询所有学科章节
     *
     * @return
     */
    @Override
    public List<Chapter> listAllChapter() {
        List<Chapter> chapterList = chapterRepository.listAll();
        chapterList.forEach(Chapter::generateSubjectTitle);
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

    /**
     * 删除一个章节和它的关联数据
     *
     * @param chapterId
     */
    @Override
    @Transactional
    public void deleteOneChapter(Long chapterId) {
        //删除题目
        questionRepository.deleteByChapterId(chapterId);
        //删除章节
        chapterRepository.deleteById(chapterId);
    }
}
