package com.jwcnetworks.bsyoo.jwc.adapter;

public class GroupData {

    //public ArrayList<ModelCafeMenu> child;
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupData(String Name){
        this.groupName = Name;
    }

}
