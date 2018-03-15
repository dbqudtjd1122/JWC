package com.jwcnetworks.bsyoo.jwc.model;


import java.io.Serializable;
import java.util.Date;

public class ModelTechnicalSupport implements Serializable {

	private Integer Number      ;
    private Integer UserNumber  ;
	private String ID           ;
	private String Question     ;
	private String Title        ;
	private String Info         ;
	private Integer Emailcheck  ;
	private Integer Smscheck    ;
	private Date Inserttime     ;
    private String ManagerID    ;
	private Date Managertime    ;
	private String Managerinfo  ;
	private Integer UpDelete    ;


    @Override
    public String toString() {
        return "ModelTechnicalSupport{" +
                "Number=" + Number +
                ", UserNumber=" + UserNumber +
                ", ID='" + ID + '\'' +
                ", Question='" + Question + '\'' +
                ", Title='" + Title + '\'' +
                ", Info='" + Info + '\'' +
                ", Emailcheck=" + Emailcheck +
                ", Smscheck=" + Smscheck +
                ", Inserttime=" + Inserttime +
                ", ManagerID='" + ManagerID + '\'' +
                ", Managertime=" + Managertime +
                ", Managerinfo='" + Managerinfo + '\'' +
                ", UpDelete=" + UpDelete +
                '}';
    }

    public ModelTechnicalSupport(Integer number, Integer userNumber, String ID, String question, String title, String info, Integer emailcheck, Integer smscheck, Date inserttime, String managerID, Date managertime, String managerinfo, Integer upDelete) {
        Number = number;
        UserNumber = userNumber;
        this.ID = ID;
        Question = question;
        Title = title;
        Info = info;
        Emailcheck = emailcheck;
        Smscheck = smscheck;
        Inserttime = inserttime;
        ManagerID = managerID;
        Managertime = managertime;
        Managerinfo = managerinfo;
        UpDelete = upDelete;
    }

    public ModelTechnicalSupport() {
    }

    public Integer getUpDelete() {
        return UpDelete;
    }

    public void setUpDelete(Integer upDelete) {
        UpDelete = upDelete;
    }

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    public Date getManagertime() {
        return Managertime;
    }

    public void setManagertime(Date managertime) {
        Managertime = managertime;
    }

    public String getManagerinfo() {
        return Managerinfo;
    }

    public void setManagerinfo(String managerinfo) {
        Managerinfo = managerinfo;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public Integer getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(Integer userNumber) {
        UserNumber = userNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public Integer getEmailcheck() {
        return Emailcheck;
    }

    public void setEmailcheck(Integer emailcheck) {
        Emailcheck = emailcheck;
    }

    public Integer getSmscheck() {
        return Smscheck;
    }

    public void setSmscheck(Integer smscheck) {
        Smscheck = smscheck;
    }

    public Date getInserttime() {
        return Inserttime;
    }

    public void setInserttime(Date inserttime) {
        Inserttime = inserttime;
    }
}
