package com.longder.edusys.entity.dto;

import com.longder.edusys.entity.po.ExamResult;
import com.longder.edusys.entity.po.ResultDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 提交考试数据的dto对象
 */
@Data
public class ExamSubmitDto implements Serializable {

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 考试结果
     */
    private List<ResultDetail> detailList;
}
