package com.example.bsyoo.jwc.model;

import java.util.Date;

public class Model_User {

    private Integer Number;
    private String ID;
    private String Email;
    private Integer Level;
    private String PW;
    private String Name;
    private String Phone_home;
    private String Phone;
    private String Addr;
    private String Mutual;
    private String Representation;
    private String Buisness_number;
    private String Sectors;
    private Date UserTime;
    private Integer Email_sms;
    private Integer Phone_sms;
    private Integer Push;
    private Integer OK;

    public Model_User(Integer number, String ID, String email, Integer level, String PW, String name, String phone_home, String phone, String addr, String mutual, String representation, String buisness_number, String sectors, Date userTime, Integer email_sms, Integer phone_sms, Integer push, Integer OK) {
        Number = number;
        this.ID = ID;
        Email = email;
        Level = level;
        this.PW = PW;
        Name = name;
        Phone_home = phone_home;
        Phone = phone;
        Addr = addr;
        Mutual = mutual;
        Representation = representation;
        Buisness_number = buisness_number;
        Sectors = sectors;
        UserTime = userTime;
        Email_sms = email_sms;
        Phone_sms = phone_sms;
        Push = push;
        this.OK = OK;
    }

    @Override
    public String toString() {
        return "Model_User{" +
                "Number=" + Number +
                ", ID='" + ID + '\'' +
                ", Email='" + Email + '\'' +
                ", Level=" + Level +
                ", PW='" + PW + '\'' +
                ", Name='" + Name + '\'' +
                ", Phone_home='" + Phone_home + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Addr='" + Addr + '\'' +
                ", Mutual='" + Mutual + '\'' +
                ", Representation='" + Representation + '\'' +
                ", Buisness_number='" + Buisness_number + '\'' +
                ", Sectors='" + Sectors + '\'' +
                ", UserTime=" + UserTime +
                ", Email_sms=" + Email_sms +
                ", Phone_sms=" + Phone_sms +
                ", Push=" + Push +
                ", OK=" + OK +
                '}';
    }

    public Model_User() {
        super();
    }


    public Date getUserTime() {
        return UserTime;
    }

    public void setUserTime(Date userTime) {
        UserTime = userTime;
    }

    public Integer getNumber() {
        return Number;
    }
    public void setNumber(Integer number) {
        Number = number;
    }
    public Integer getOK() {
        return OK;
    }
    public void setOK(Integer oK) {
        OK = oK;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public Integer getLevel() {
        return Level;
    }
    public void setLevel(Integer level) {
        Level = level;
    }
    public String getPW() {
        return PW;
    }
    public void setPW(String pW) {
        PW = pW;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getPhone_home() {
        return Phone_home;
    }
    public void setPhone_home(String phone_home) {
        Phone_home = phone_home;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getAddr() {
        return Addr;
    }
    public void setAddr(String addr) {
        Addr = addr;
    }
    public String getMutual() {
        return Mutual;
    }
    public void setMutual(String mutual) {
        Mutual = mutual;
    }
    public String getRepresentation() {
        return Representation;
    }
    public void setRepresentation(String representation) {
        Representation = representation;
    }
    public String getBuisness_number() {
        return Buisness_number;
    }
    public void setBuisness_number(String buisness_number) {
        Buisness_number = buisness_number;
    }
    public String getSectors() {
        return Sectors;
    }
    public void setSectors(String sectors) {
        Sectors = sectors;
    }
    public Integer getEmail_sms() {
        return Email_sms;
    }
    public void setEmail_sms(Integer email_sms) {
        Email_sms = email_sms;
    }
    public Integer getPhone_sms() {
        return Phone_sms;
    }
    public void setPhone_sms(Integer phone_sms) {
        Phone_sms = phone_sms;
    }
    public Integer getPush() {
        return Push;
    }
    public void setPush(Integer push) {
        Push = push;
    }


}
