package com.example.bsyoo.jwc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ModelUserSerialCode implements Parcelable{

	private Integer Serial_Number ;
	private Integer User_Number   ;
	private String Cameratype     ; // 제품 타입
	private String Onlineseries   ; // 시리즈 이름
	private String Onlinename     ; // 제품 이름
	private String Serial_Code    ;
    private String Img_title      ;
    private String Company_Info   ;  // 구입처


    protected ModelUserSerialCode(Parcel in) {
        Cameratype = in.readString();
        Onlineseries = in.readString();
        Onlinename = in.readString();
        Serial_Code = in.readString();
        Img_title = in.readString();
        Company_Info = in.readString();
    }

    public static final Creator<ModelUserSerialCode> CREATOR = new Creator<ModelUserSerialCode>() {
        @Override
        public ModelUserSerialCode createFromParcel(Parcel in) {
            return new ModelUserSerialCode(in);
        }

        @Override
        public ModelUserSerialCode[] newArray(int size) {
            return new ModelUserSerialCode[size];
        }
    };

    @Override
    public String toString() {
        return "ModelUserSerialCode{" +
                "Serial_Number=" + Serial_Number +
                ", User_Number=" + User_Number +
                ", Cameratype='" + Cameratype + '\'' +
                ", Onlineseries='" + Onlineseries + '\'' +
                ", Onlinename='" + Onlinename + '\'' +
                ", Serial_Code='" + Serial_Code + '\'' +
                ", Img_title='" + Img_title + '\'' +
                ", Company_Info='" + Company_Info + '\'' +
                '}';
    }

    public ModelUserSerialCode(Integer serial_Number, Integer user_Number, String cameratype, String onlineseries, String onlinename, String serial_Code, String img_title, String company_Info) {
        Serial_Number = serial_Number;
        User_Number = user_Number;
        Cameratype = cameratype;
        Onlineseries = onlineseries;
        Onlinename = onlinename;
        Serial_Code = serial_Code;
        Img_title = img_title;
        Company_Info = company_Info;
    }

    public ModelUserSerialCode() {
    }

    public String getCompany_Info() {
        return Company_Info;
    }

    public void setCompany_Info(String company_Info) {
        Company_Info = company_Info;
    }

    public String getImg_title() {
        return Img_title;
    }

    public void setImg_title(String img_title) {
        Img_title = img_title;
    }

    public Integer getSerial_Number() {
        return Serial_Number;
    }

    public void setSerial_Number(Integer serial_Number) {
        Serial_Number = serial_Number;
    }

    public String getSerial_Code() {
        return Serial_Code;
    }

    public void setSerial_Code(String serial_Code) {
        Serial_Code = serial_Code;
    }

    public Integer getUser_Number() {
        return User_Number;
    }

    public void setUser_Number(Integer user_Number) {
        User_Number = user_Number;
    }

    public String getCameratype() {
        return Cameratype;
    }

    public void setCameratype(String cameratype) {
        Cameratype = cameratype;
    }

    public String getOnlineseries() {
        return Onlineseries;
    }

    public void setOnlineseries(String onlineseries) {
        Onlineseries = onlineseries;
    }

    public String getOnlinename() {
        return Onlinename;
    }

    public void setOnlinename(String onlinename) {
        Onlinename = onlinename;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Cameratype);
        dest.writeString(Onlineseries);
        dest.writeString(Onlinename);
        dest.writeString(Serial_Code);
        dest.writeString(Img_title);
        dest.writeString(Company_Info);
    }
}
