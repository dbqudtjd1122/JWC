package com.example.bsyoo.jwc.model;

import java.io.Serializable;

public class ModelCamera implements Serializable{


	private Integer Number           ;
	private String  Cameratype       ;
	private Integer Level            ;
	private String  Onlineseries     ;
	private String  Offlineseries    ;
	private String  Onlinename       ;
	private String  Offlinename      ;
    private String  Signaltype       ;
	private String  Level1price      ;
	private String  Level2price      ;
	private String  Level3price      ;
	private String  Level4price      ;
    private String  Online_Img_title ;
	private String  Offline_Img_title;
	private String  Online_Img_info  ;
	private String  Offline_Img_info ;
    private String  Youtube          ;
    private String  NewCamera        ;

    @Override
    public String toString() {
        return "ModelCamera{" +
                "Number=" + Number +
                ", Cameratype='" + Cameratype + '\'' +
                ", Level=" + Level +
                ", Onlineseries='" + Onlineseries + '\'' +
                ", Offlineseries='" + Offlineseries + '\'' +
                ", Onlinename='" + Onlinename + '\'' +
                ", Offlinename='" + Offlinename + '\'' +
                ", Signaltype='" + Signaltype + '\'' +
                ", Level1price='" + Level1price + '\'' +
                ", Level2price='" + Level2price + '\'' +
                ", Level3price='" + Level3price + '\'' +
                ", Level4price='" + Level4price + '\'' +
                ", Online_Img_title='" + Online_Img_title + '\'' +
                ", Offline_Img_title='" + Offline_Img_title + '\'' +
                ", Online_Img_info='" + Online_Img_info + '\'' +
                ", Offline_Img_info='" + Offline_Img_info + '\'' +
                ", Youtube='" + Youtube + '\'' +
                ", NewCamera='" + NewCamera + '\'' +
                '}';
    }

    public ModelCamera(Integer number, String cameratype, Integer level, String onlineseries, String offlineseries, String onlinename, String offlinename, String signaltype, String level1price, String level2price, String level3price, String level4price, String online_Img_title, String offline_Img_title, String online_Img_info, String offline_Img_info, String youtube, String newCamera) {
        Number = number;
        Cameratype = cameratype;
        Level = level;
        Onlineseries = onlineseries;
        Offlineseries = offlineseries;
        Onlinename = onlinename;
        Offlinename = offlinename;
        Signaltype = signaltype;
        Level1price = level1price;
        Level2price = level2price;
        Level3price = level3price;
        Level4price = level4price;
        Online_Img_title = online_Img_title;
        Offline_Img_title = offline_Img_title;
        Online_Img_info = online_Img_info;
        Offline_Img_info = offline_Img_info;
        Youtube = youtube;
        NewCamera = newCamera;
    }

    public ModelCamera() {
    }

    public String getYoutube() {
        return Youtube;
    }

    public void setYoutube(String youtube) {
        Youtube = youtube;
    }

    public String getSignaltype() {
        return Signaltype;
    }

    public void setSignaltype(String signaltype) {
        Signaltype = signaltype;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public String getCameratype() {
        return Cameratype;
    }

    public void setCameratype(String cameratype) {
        Cameratype = cameratype;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public String getOnlineseries() {
        return Onlineseries;
    }

    public void setOnlineseries(String onlineseries) {
        Onlineseries = onlineseries;
    }

    public String getOfflineseries() {
        return Offlineseries;
    }

    public void setOfflineseries(String offlineseries) {
        Offlineseries = offlineseries;
    }

    public String getOnlinename() {
        return Onlinename;
    }

    public void setOnlinename(String onlinename) {
        Onlinename = onlinename;
    }

    public String getOfflinename() {
        return Offlinename;
    }

    public void setOfflinename(String offlinename) {
        Offlinename = offlinename;
    }

    public String getLevel1price() {
        return Level1price;
    }

    public void setLevel1price(String level1price) {
        Level1price = level1price;
    }

    public String getLevel2price() {
        return Level2price;
    }

    public void setLevel2price(String level2price) {
        Level2price = level2price;
    }

    public String getLevel3price() {
        return Level3price;
    }

    public void setLevel3price(String level3price) {
        Level3price = level3price;
    }

    public String getLevel4price() {
        return Level4price;
    }

    public void setLevel4price(String level4price) {
        Level4price = level4price;
    }

    public String getOnline_Img_title() {
        return Online_Img_title;
    }

    public void setOnline_Img_title(String online_Img_title) {
        Online_Img_title = online_Img_title;
    }

    public String getOffline_Img_title() {
        return Offline_Img_title;
    }

    public void setOffline_Img_title(String offline_Img_title) {
        Offline_Img_title = offline_Img_title;
    }

    public String getOnline_Img_info() {
        return Online_Img_info;
    }

    public void setOnline_Img_info(String online_Img_info) {
        Online_Img_info = online_Img_info;
    }

    public String getOffline_Img_info() {
        return Offline_Img_info;
    }

    public void setOffline_Img_info(String offline_Img_info) {
        Offline_Img_info = offline_Img_info;
    }

    public String getNewCamera() {
        return NewCamera;
    }

    public void setNewCamera(String newCamera) {
        NewCamera = newCamera;
    }
}
