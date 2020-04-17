package com.longder.edusys.entity.dto;

import com.longder.edusys.entity.enums.ChooseWay;
import com.longder.edusys.entity.enums.ExamType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 初始化考试的dto
 */
@Data
public class ExamInitDto implements Serializable {
    /**
     * 考试名称
     */
    private String name;
    /**
     * 班级id
     */
    private Long classId;
}
