package com.example.bsyoo.jwc.model;

import java.io.Serializable;
import java.util.Date;

public class ModelTechnology implements Serializable {

    private Integer YouTubeNumber;
    private String 	YouTubeName  ;
    private String 	YouTubeUrl   ;
    private Date    UrlTime      ;


    @Override
    public String toString() {
        return "ModelTechnology{" +
                "YouTubeNumber=" + YouTubeNumber +
                ", YouTubeName='" + YouTubeName + '\'' +
                ", YouTubeUrl='" + YouTubeUrl + '\'' +
                ", UrlTime=" + UrlTime +
                '}';
    }

    public ModelTechnology(Integer youTubeNumber, String youTubeName, String youTubeUrl, Date urlTime) {
        YouTubeNumber = youTubeNumber;
        YouTubeName = youTubeName;
        YouTubeUrl = youTubeUrl;
        UrlTime = urlTime;
    }

    public ModelTechnology() {
    }


    public Date getUrlTime() {
        return UrlTime;
    }

    public void setUrlTime(Date urlTime) {
        UrlTime = urlTime;
    }

    public Integer getYouTubeNumber() {
        return YouTubeNumber;
    }

    public void setYouTubeNumber(Integer youTubeNumber) {
        YouTubeNumber = youTubeNumber;
    }

    public String getYouTubeName() {
        return YouTubeName;
    }

    public void setYouTubeName(String youTubeName) {
        YouTubeName = youTubeName;
    }

    public String getYouTubeUrl() {
        return YouTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        YouTubeUrl = youTubeUrl;
    }
}
