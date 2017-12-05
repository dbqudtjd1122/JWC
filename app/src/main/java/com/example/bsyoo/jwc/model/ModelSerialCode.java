package com.example.bsyoo.jwc.model;


public class ModelSerialCode {

     private String Cameratype    ;
     private String Onlineseries  ;
     private String Onlinename    ;
     private String Offlinename   ;
     private String Serial_Code   ;


    @Override
    public String toString() {
        return "ModelSerialCode{" +
                "Cameratype='" + Cameratype + '\'' +
                ", Onlineseries='" + Onlineseries + '\'' +
                ", Onlinename='" + Onlinename + '\'' +
                ", Offlinename='" + Offlinename + '\'' +
                ", Serial_Code='" + Serial_Code + '\'' +
                '}';
    }

    public ModelSerialCode() {
    }

    public ModelSerialCode(String cameratype, String onlineseries, String onlinename, String offlinename, String serial_Code) {
        Cameratype = cameratype;
        Onlineseries = onlineseries;
        Onlinename = onlinename;
        Offlinename = offlinename;
        Serial_Code = serial_Code;
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

    public String getOfflinename() {
        return Offlinename;
    }

    public void setOfflinename(String offlinename) {
        Offlinename = offlinename;
    }

    public String getSerial_Code() {
        return Serial_Code;
    }

    public void setSerial_Code(String serial_Code) {
        Serial_Code = serial_Code;
    }
}
