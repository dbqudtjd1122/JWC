package com.jwcnetworks.bsyoo.jwc.model;

import java.io.Serializable;
import java.util.Date;

public class ModelTechnology implements Serializable {

    private Integer YouTubeNumber;
    private String  CameraType   ;
    private String 	YouTubeName  ;
    private String 	YouTubeUrl   ;
    private Date    UrlTime      ;

    @Override
    public String toString() {
        return "ModelTechnology{" +
                "YouTubeNumber=" + YouTubeNumber +
                ", CameraType='" + CameraType + '\'' +
                ", YouTubeName='" + YouTubeName + '\'' +
                ", YouTubeUrl='" + YouTubeUrl + '\'' +
                ", UrlTime=" + UrlTime +
                '}';
    }

    public ModelTechnology(Integer youTubeNumber, String cameraType, String youTubeName, String youTubeUrl, Date urlTime) {
        YouTubeNumber = youTubeNumber;
        CameraType = cameraType;
        YouTubeName = youTubeName;
        YouTubeUrl = youTubeUrl;
        UrlTime = urlTime;
    }

    public ModelTechnology() {
    }


    public String getCameraType() {
        return CameraType;
    }

    public void setCameraType(String cameraType) {
        CameraType = cameraType;
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
