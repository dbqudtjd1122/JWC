package com.example.bsyoo.jwc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Model_Notice implements Serializable {

    private String title;
    private String notice;
    private String day;

    protected Model_Notice(Parcel in) {
        notice = in.readString();
        day = in.readString();
    }

    @Override
    public String toString() {
        return "Model_Notice{" +
                "notice='" + notice + '\'' +
                ", day='" + day + '\'' +
                '}';
    }

    public Model_Notice() {
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Model_Notice(String notice, String day) {
        this.notice = notice;
        this.day = day;
    }

}
