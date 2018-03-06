package com.jwcnetworks.bsyoo.jwc.model;


import java.util.Date;

public class ModelPushToken {

    private Integer number      ;
	private String pushtitle   ;
	private String pushmessage ;
	private Date pushtime       ;
    private String userid      ;
	private Integer userlevel   ;
	private String reception   ;
    private String inoutpush   ;


    @Override
    public String toString() {
        return "ModelPushToken{" +
                "number=" + number +
                ", pushtitle='" + pushtitle + '\'' +
                ", pushmessage='" + pushmessage + '\'' +
                ", pushtime=" + pushtime +
                ", userid='" + userid + '\'' +
                ", userlevel=" + userlevel +
                ", reception='" + reception + '\'' +
                ", inoutpush='" + inoutpush + '\'' +
                '}';
    }

    public ModelPushToken(Integer number, String pushtitle, String pushmessage, Date pushtime, String userid, Integer userlevel, String reception, String inoutpush) {
        this.number = number;
        this.pushtitle = pushtitle;
        this.pushmessage = pushmessage;
        this.pushtime = pushtime;
        this.userid = userid;
        this.userlevel = userlevel;
        this.reception = reception;
        this.inoutpush = inoutpush;
    }

    public ModelPushToken() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPushtitle() {
        return pushtitle;
    }

    public void setPushtitle(String pushtitle) {
        this.pushtitle = pushtitle;
    }

    public String getPushmessage() {
        return pushmessage;
    }

    public void setPushmessage(String pushmessage) {
        this.pushmessage = pushmessage;
    }

    public Date getPushtime() {
        return pushtime;
    }

    public void setPushtime(Date pushtime) {
        this.pushtime = pushtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public String getReception() {
        return reception;
    }

    public void setReception(String reception) {
        this.reception = reception;
    }

    public String getInoutpush() {
        return inoutpush;
    }

    public void setInoutpush(String inoutpush) {
        this.inoutpush = inoutpush;
    }
}
