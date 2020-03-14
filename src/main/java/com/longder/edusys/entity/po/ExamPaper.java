package com.longder.edusys.entity.po;

import com.longder.edusys.entity.enums.ExamType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 考试试卷
 * 自测和正式考试都会先生成试卷
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExamPaper extends BaseIdEntity{

    /**
     * 自测的考试没有名称
     */
    private String name;

    /**
     * 关联班级(一定有)
     */
    private GradeClass gradeClass;

    /**
     * 关联学生（自测才有）
     */
    private SysUser student;

    /**
     * 考试类型
     */
    private ExamType examType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间展示用的字符串
     */
    private String createTimeStr;

    /**
     * 试卷详情，展示用
     */
    private List<PaperDetail> detailList;

    /**
     * 参考学生人数，展示用
     */
    private Integer studentCount;

    /**
     * 处理
     */
    public void generateCreateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createTimeStr = formatter.format(this.getCreateTime());
    }

}
