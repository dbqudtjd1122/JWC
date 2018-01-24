package com.jwcnetworks.bsyoo.jwc.model;


import java.io.Serializable;
import java.util.Date;

public class ModelAgencyTopic implements Serializable {

    private Integer Topic_Number  ;
	private String Topic_Name     ;
	private String Information    ;
	private Date Topic_Time       ;
	private Integer Love          ;
    private Integer review        ;
	private String ID             ;
	private String Img1           ;
	private String Img2           ;
	private String Img3           ;


    @Override
    public String toString() {
        return "ModelAgencyTopic{" +
                "Topic_Number=" + Topic_Number +
                ", Topic_Name='" + Topic_Name + '\'' +
                ", Information='" + Information + '\'' +
                ", Topic_Time=" + Topic_Time +
                ", Love=" + Love +
                ", review=" + review +
                ", ID='" + ID + '\'' +
                ", Img1='" + Img1 + '\'' +
                ", Img2='" + Img2 + '\'' +
                ", Img3='" + Img3 + '\'' +
                '}';
    }

    public ModelAgencyTopic(Integer topic_Number, String topic_Name, String information, Date topic_Time, Integer love, Integer review, String ID, String img1, String img2, String img3) {
        Topic_Number = topic_Number;
        Topic_Name = topic_Name;
        Information = information;
        Topic_Time = topic_Time;
        Love = love;
        this.review = review;
        this.ID = ID;
        Img1 = img1;
        Img2 = img2;
        Img3 = img3;
    }

    public ModelAgencyTopic() {
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public Integer getTopic_Number() {
        return Topic_Number;
    }

    public void setTopic_Number(Integer topic_Number) {
        Topic_Number = topic_Number;
    }

    public String getTopic_Name() {
        return Topic_Name;
    }

    public void setTopic_Name(String topic_Name) {
        Topic_Name = topic_Name;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public Date getTopic_Time() {
        return Topic_Time;
    }

    public void setTopic_Time(Date topic_Time) {
        Topic_Time = topic_Time;
    }

    public Integer getLove() {
        return Love;
    }

    public void setLove(Integer love) {
        Love = love;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getImg3() {
        return Img3;
    }

    public void setImg3(String img3) {
        Img3 = img3;
    }
}
