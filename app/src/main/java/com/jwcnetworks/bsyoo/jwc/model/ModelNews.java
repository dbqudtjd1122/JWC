package com.jwcnetworks.bsyoo.jwc.model;


import java.io.Serializable;
import java.util.Date;

public class ModelNews implements Serializable {

    private Integer number    ;
	private String newstitle  ;
	private String info1      ;
	private String image1     ;
	private String info2      ;
	private String image2     ;
	private String info3      ;
	private String source     ;
	private String link       ;
	private Date   newstime   ;


    @Override
    public String toString() {
        return "ModelNews{" +
                "number=" + number +
                ", newstitle='" + newstitle + '\'' +
                ", info1='" + info1 + '\'' +
                ", image1='" + image1 + '\'' +
                ", info2='" + info2 + '\'' +
                ", image2='" + image2 + '\'' +
                ", info3='" + info3 + '\'' +
                ", source='" + source + '\'' +
                ", link='" + link + '\'' +
                ", newstime=" + newstime +
                '}';
    }

    public ModelNews(Integer number, String newstitle, String info1, String image1, String info2, String image2, String info3, String source, String link, Date newstime) {
        this.number = number;
        this.newstitle = newstitle;
        this.info1 = info1;
        this.image1 = image1;
        this.info2 = info2;
        this.image2 = image2;
        this.info3 = info3;
        this.source = source;
        this.link = link;
        this.newstime = newstime;
    }

    public ModelNews() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getNewstime() {
        return newstime;
    }

    public void setNewstime(Date newstime) {
        this.newstime = newstime;
    }
}
