package com.longder.edusys.entity.enums;

import java.util.Arrays;

/**
 * 考试类型
 */
public enum ExamType {
    SELF("SELF", "", "自我测验"),
    NORMAL("NORMAL", "", "正式考试");


    private String name;

    private String label;

    private String displayName;


    ExamType(String name, String label, String displayName) {
        this.name = name;
        this.label = label;
        this.displayName = displayName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean equals(QuestionType questionType){
        return this.name.equals(questionType.getName());
    }
}
