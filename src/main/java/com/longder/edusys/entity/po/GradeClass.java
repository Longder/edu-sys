package com.longder.edusys.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班级实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GradeClass extends BaseIdEntity{
    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级描述
     */
    private String description;
}
