package com.example.bsyoo.jwc.model;

import java.util.Date;

public class ModelAgencyTopicReview {

    private Integer Review_Number ;
	private Integer Topic_Number  ;
	private String  ID            ;
	private Date    Review_Time   ;
	private String  Information   ;


    @Override
    public String toString() {
        return "ModelAgencyTopicReview{" +
                "Review_Number=" + Review_Number +
                ", Topic_Number=" + Topic_Number +
                ", ID='" + ID + '\'' +
                ", Review_Time=" + Review_Time +
                ", Information='" + Information + '\'' +
                '}';
    }

    public ModelAgencyTopicReview() {
    }

    public ModelAgencyTopicReview(Integer review_Number, Integer topic_Number, String ID, Date review_Time, String information) {
        Review_Number = review_Number;
        Topic_Number = topic_Number;
        this.ID = ID;
        Review_Time = review_Time;
        Information = information;
    }

    public Integer getReview_Number() {
        return Review_Number;
    }

    public void setReview_Number(Integer review_Number) {
        Review_Number = review_Number;
    }

    public Integer getTopic_Number() {
        return Topic_Number;
    }

    public void setTopic_Number(Integer topic_Number) {
        Topic_Number = topic_Number;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getReview_Time() {
        return Review_Time;
    }

    public void setReview_Time(Date review_Time) {
        Review_Time = review_Time;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }
}
