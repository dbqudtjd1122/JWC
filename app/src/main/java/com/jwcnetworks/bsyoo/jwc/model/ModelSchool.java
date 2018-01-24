package com.jwcnetworks.bsyoo.jwc.model;


import java.io.Serializable;
import java.util.Date;

public class ModelSchool implements Serializable {

    private Integer School_Number;
	private String Start_End     ;
	private String School_Title  ;
	private String Apply_Time    ;
	private Date Lecture_Time    ;
	private String Img_title     ;
	private String Img_info      ;
    private String Team          ;
    private String Manager       ;
    private String ManagerPhone  ;


    @Override
    public String toString() {
        return "ModelSchool{" +
                "School_Number=" + School_Number +
                ", Start_End='" + Start_End + '\'' +
                ", School_Title='" + School_Title + '\'' +
                ", Apply_Time='" + Apply_Time + '\'' +
                ", Lecture_Time=" + Lecture_Time +
                ", Img_title='" + Img_title + '\'' +
                ", Img_info='" + Img_info + '\'' +
                ", Team='" + Team + '\'' +
                ", Manager='" + Manager + '\'' +
                ", ManagerPhone='" + ManagerPhone + '\'' +
                '}';
    }

    public ModelSchool(Integer school_Number, String start_End, String school_Title, String apply_Time, Date lecture_Time, String img_title, String img_info, String team, String manager, String managerPhone) {
        School_Number = school_Number;
        Start_End = start_End;
        School_Title = school_Title;
        Apply_Time = apply_Time;
        Lecture_Time = lecture_Time;
        Img_title = img_title;
        Img_info = img_info;
        Team = team;
        Manager = manager;
        ManagerPhone = managerPhone;
    }

    public ModelSchool() {
    }


    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getManagerPhone() {
        return ManagerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        ManagerPhone = managerPhone;
    }

    public Integer getSchool_Number() {
        return School_Number;
    }

    public void setSchool_Number(Integer school_Number) {
        School_Number = school_Number;
    }

    public String getStart_End() {
        return Start_End;
    }

    public void setStart_End(String start_End) {
        Start_End = start_End;
    }

    public String getSchool_Title() {
        return School_Title;
    }

    public void setSchool_Title(String school_Title) {
        School_Title = school_Title;
    }

    public String getApply_Time() {
        return Apply_Time;
    }

    public void setApply_Time(String apply_Time) {
        Apply_Time = apply_Time;
    }

    public Date getLecture_Time() {
        return Lecture_Time;
    }

    public void setLecture_Time(Date lecture_Time) {
        Lecture_Time = lecture_Time;
    }

    public String getImg_title() {
        return Img_title;
    }

    public void setImg_title(String img_title) {
        Img_title = img_title;
    }

    public String getImg_info() {
        return Img_info;
    }

    public void setImg_info(String img_info) {
        Img_info = img_info;
    }
}
