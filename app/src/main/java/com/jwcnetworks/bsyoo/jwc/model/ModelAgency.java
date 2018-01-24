package com.jwcnetworks.bsyoo.jwc.model;


public class ModelAgency {


	private Integer Agency_Number ;
	private String Agency_Name    ;
	private String Information    ;
	private String Area           ;
	private String Addr           ;
	private String Phone          ;
	private String Img_Title      ;

    @Override
    public String toString() {
        return "ModelAgency{" +
                "Agency_Number=" + Agency_Number +
                ", Agency_Name='" + Agency_Name + '\'' +
                ", Information='" + Information + '\'' +
                ", Area='" + Area + '\'' +
                ", Addr='" + Addr + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Img_Title='" + Img_Title + '\'' +
                '}';
    }

    public ModelAgency() {
    }

    public ModelAgency(Integer agency_Number, String agency_Name, String information, String area, String addr, String phone, String img_Title) {
        Agency_Number = agency_Number;
        Agency_Name = agency_Name;
        Information = information;
        Area = area;
        Addr = addr;
        Phone = phone;
        Img_Title = img_Title;
    }

    public Integer getAgency_Number() {
        return Agency_Number;
    }

    public void setAgency_Number(Integer agency_Number) {
        Agency_Number = agency_Number;
    }

    public String getAgency_Name() {
        return Agency_Name;
    }

    public void setAgency_Name(String agency_Name) {
        Agency_Name = agency_Name;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImg_Title() {
        return Img_Title;
    }

    public void setImg_Title(String img_Title) {
        Img_Title = img_Title;
    }
}