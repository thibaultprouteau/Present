package com.example.attendance;

public class Groups {

    private Integer idGroup;
    private String groupName;

    public Groups(Integer idGroup, String groupName) {
        this.idGroup = idGroup;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}
