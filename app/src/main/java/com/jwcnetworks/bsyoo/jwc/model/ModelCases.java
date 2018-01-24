package com.jwcnetworks.bsyoo.jwc.model;

public class ModelCases {

    private Integer Cases_Numbe;
	private Integer User_Number;
	private Integer Level      ;
	private String Title_Name  ;
	private String Topic       ;
	private String Blog_URL    ;


    @Override
    public String toString() {
        return "ModelCases{" +
                "Cases_Numbe=" + Cases_Numbe +
                ", User_Number=" + User_Number +
                ", Level=" + Level +
                ", Title_Name='" + Title_Name + '\'' +
                ", Topic='" + Topic + '\'' +
                ", Blog_URL='" + Blog_URL + '\'' +
                '}';
    }

    public ModelCases() {
    }

    public ModelCases(Integer cases_Numbe, Integer user_Number, Integer level, String title_Name, String topic, String blog_URL) {
        Cases_Numbe = cases_Numbe;
        User_Number = user_Number;
        Level = level;
        Title_Name = title_Name;
        Topic = topic;
        Blog_URL = blog_URL;
    }

    public Integer getCases_Numbe() {
        return Cases_Numbe;
    }

    public void setCases_Numbe(Integer cases_Numbe) {
        Cases_Numbe = cases_Numbe;
    }

    public Integer getUser_Number() {
        return User_Number;
    }

    public void setUser_Number(Integer user_Number) {
        User_Number = user_Number;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public String getTitle_Name() {
        return Title_Name;
    }

    public void setTitle_Name(String title_Name) {
        Title_Name = title_Name;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getBlog_URL() {
        return Blog_URL;
    }

    public void setBlog_URL(String blog_URL) {
        Blog_URL = blog_URL;
    }
}
