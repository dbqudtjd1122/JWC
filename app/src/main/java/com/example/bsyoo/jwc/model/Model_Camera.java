package com.example.bsyoo.jwc.model;

import java.io.Serializable;

public class Model_Camera implements Serializable{

    private Integer Camerano;
    private String type;
    private String onlinename;
    private String offlinename;
    private String onlineprice;
    private String offlineprice;
    private String series;
    private String Performance; // 성능
    private String Pixel;
    private Integer NewCamera;

    public Model_Camera(Integer camerano, String type, String onlinename, String offlinename, String onlineprice, String offlineprice, String series, String performance, String pixel, Integer newCamera) {
        Camerano = camerano;
        this.type = type;
        this.onlinename = onlinename;
        this.offlinename = offlinename;
        this.onlineprice = onlineprice;
        this.offlineprice = offlineprice;
        this.series = series;
        Performance = performance;
        Pixel = pixel;
        NewCamera = newCamera;
    }

    @Override
    public String toString() {
        return "Model_Camera{" +
                "Camerano=" + Camerano +
                ", type='" + type + '\'' +
                ", onlinename='" + onlinename + '\'' +
                ", offlinename='" + offlinename + '\'' +
                ", onlineprice='" + onlineprice + '\'' +
                ", offlineprice='" + offlineprice + '\'' +
                ", series='" + series + '\'' +
                ", Performance='" + Performance + '\'' +
                ", Pixel='" + Pixel + '\'' +
                ", NewCamera=" + NewCamera +
                '}';
    }

    public Model_Camera() {
    }

    public Integer getNewCamera() {
        return NewCamera;
    }

    public void setNewCamera(Integer newCamera) {
        NewCamera = newCamera;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerformance() {
        return Performance;
    }
    public void setPerformance(String performance) {
        Performance = performance;
    }
    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }
    public Integer getCamerano() {
        return Camerano;
    }

    public void setCamerano(Integer camerano) {
        Camerano = camerano;
    }

    public String getOnlinename() {
        return onlinename;
    }

    public void setOnlinename(String onlinename) {
        this.onlinename = onlinename;
    }

    public String getOfflinename() {
        return offlinename;
    }

    public void setOfflinename(String offlinename) {
        this.offlinename = offlinename;
    }

    public String getOnlineprice() {
        return onlineprice;
    }

    public void setOnlineprice(String onlineprice) {
        this.onlineprice = onlineprice;
    }

    public String getOfflineprice() {
        return offlineprice;
    }

    public void setOfflineprice(String offlineprice) {
        this.offlineprice = offlineprice;
    }

    public String getPixel() {
        return Pixel;
    }

    public void setPixel(String pixel) {
        Pixel = pixel;
    }
}
