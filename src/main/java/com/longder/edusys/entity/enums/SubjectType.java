package com.longder.edusys.entity.enums;

/**
 * 学科类型
 */
public enum SubjectType {
    higher_mathematics("higher_mathematics","","高等数学"),
    physics("physics","","大学物理"),
    computer("computer","","计算机科学");

    private String name;

    private String label;

    private String displayName;


    SubjectType(String name, String label, String displayName) {
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

    public boolean equals(SubjectType subjectType){
        return this.name.equals(subjectType.getName());
    }
}
