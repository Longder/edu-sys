package com.longder.edusys.entity.dto;

import com.longder.edusys.entity.po.ResultDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 考试结果展示的dto
 */
@Data
public class ExamResultDto implements Serializable {

    /**
     * 考试名字
     */
    private String examName;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 详情
     */
    private List<ResultDetail> detailList;
}
