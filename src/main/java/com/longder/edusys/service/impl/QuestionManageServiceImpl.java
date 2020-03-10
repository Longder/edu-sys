package com.longder.edusys.service.impl;

import com.longder.edusys.entity.dto.QuestionCountDto;
import com.longder.edusys.entity.po.Question;
import com.longder.edusys.entity.vo.QuestionWrongCountListVo;
import com.longder.edusys.repository.QuestionRepository;
import com.longder.edusys.repository.ResultDetailRepository;
import com.longder.edusys.service.QuestionManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionManageServiceImpl implements QuestionManageService {

    @Resource
    private QuestionRepository questionRepository;
    @Resource
    private ResultDetailRepository resultDetailRepository;

    /**
     * 添加一个习题
     *
     * @param question
     */
    @Override
    @Transactional
    public void addOneQuestion(Question question) {
        questionRepository.insert(question);
    }

    /**
     * 查询所有习题
     *
     * @return
     */
    @Override
    public List<Question> listAllQuestion() {
        List<Question> questionList = questionRepository.listAll();
        //处理习题关联的chapter展示问题
        questionList.forEach(question -> question.getChapter().generateSubjectTitle());
        return questionList;
    }

    /**
     * 全章节随机选题，保证满分
     *
     * @return
     */
    @Override
    public List<Question> randomAllQuestion() {
        List<Question> questionList = questionRepository.listAll();
        return fillQuestionList(questionList);
    }

    /**
     * 在指定章节中选题
     *
     * @param chapterIds
     * @return
     */
    @Override
    public List<Question> randomAssignQuestion(List<Long> chapterIds) {
        List<Question> questionList = questionRepository.listByChapterIds(chapterIds);
        return fillQuestionList(questionList);
    }

    /**
     * 查询获取一个题目
     *
     * @param questionId
     * @return
     */
    @Override
    public Question getOneQuestion(Long questionId) {
        return questionRepository.getOne(questionId);
    }

    /**
     * 错题统计列表展示
     * @param studentId
     * @return
     */
    @Override
    public List<QuestionWrongCountListVo> listWrongQuestionCount(Long studentId) {
        List<QuestionWrongCountListVo> voList = new ArrayList<>();
        //统计
        List<QuestionCountDto> countList = resultDetailRepository.countWrongQuestionByStudentId(studentId);
        //封装
        countList.forEach(dto -> {
            Question question = questionRepository.getOne(dto.getQuestionId());
            QuestionWrongCountListVo vo = new QuestionWrongCountListVo();
            vo.setQuestion(question);
            question.getChapter().generateSubjectTitle();
            vo.setChapter(question.getChapter());
            vo.setCount(dto.getCount());
            voList.add(vo);
        });
        return voList;
    }

    /**
     * 随机选题20次
     *
     * @return
     */
    private List<Question> fillQuestionList(List<Question> sourceList) {
        List<Question> resultList = new ArrayList<>();
        Random random = new Random();
        //生成20个随机的index，不能重复
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(sourceList.size());
            if (!indexList.contains(index)) {
                indexList.add(index);
            } else {
                i--;
            }
        }
        indexList.forEach(index -> {
            resultList.add(sourceList.get(index));
        });

        return resultList;
    }
}
