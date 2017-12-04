package com.example.bsyoo.jwc.model;

public class ModelSerialCode {

	private Integer Serial_Number ;
	private Integer User_Number   ;
	private String Cameratype     ; // 제품 타입
	private String Onlineseries   ; // 시리즈 이름
	private String Onlinename     ; // 제품 이름
	private String Serial_Code    ;
    private String Img_title      ;



    @Override
    public String toString() {
        return "ModelSerialCode{" +
                "Serial_Number=" + Serial_Number +
                ", User_Number=" + User_Number +
                ", Cameratype='" + Cameratype + '\'' +
                ", Onlineseries='" + Onlineseries + '\'' +
                ", Onlinename='" + Onlinename + '\'' +
                ", Serial_Code='" + Serial_Code + '\'' +
                ", Img_title='" + Img_title + '\'' +
                '}';
    }

    public ModelSerialCode(Integer serial_Number, Integer user_Number, String cameratype, String onlineseries, String onlinename, String serial_Code, String img_title) {
        Serial_Number = serial_Number;
        User_Number = user_Number;
        Cameratype = cameratype;
        Onlineseries = onlineseries;
        Onlinename = onlinename;
        Serial_Code = serial_Code;
        Img_title = img_title;
    }

    public ModelSerialCode() {
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
}
