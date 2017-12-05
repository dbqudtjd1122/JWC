package com.example.bsyoo.jwc.model;


public class ModelUserSchool {

    private Integer Number        ;
	private Integer School_Number ;
	private Integer User_Number   ;
	private Integer User_Personnel;
	private String User_Blog      ;
	private String User_Motive    ;


    @Override
    public String toString() {
        return "ModelUserSchool{" +
                "Number=" + Number +
                ", School_Number=" + School_Number +
                ", User_Number=" + User_Number +
                ", User_Personnel=" + User_Personnel +
                ", User_Blog='" + User_Blog + '\'' +
                ", User_Motive='" + User_Motive + '\'' +
                '}';
    }

    public ModelUserSchool() {
    }

    public ModelUserSchool(Integer number, Integer school_Number, Integer user_Number, Integer user_Personnel, String user_Blog, String user_Motive) {
        Number = number;
        School_Number = school_Number;
        User_Number = user_Number;
        User_Personnel = user_Personnel;
        User_Blog = user_Blog;
        User_Motive = user_Motive;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public Integer getSchool_Number() {
        return School_Number;
    }

    public void setSchool_Number(Integer school_Number) {
        School_Number = school_Number;
    }

    public Integer getUser_Number() {
        return User_Number;
    }

    public void setUser_Number(Integer user_Number) {
        User_Number = user_Number;
    }

    public Integer getUser_Personnel() {
        return User_Personnel;
    }

    public void setUser_Personnel(Integer user_Personnel) {
        User_Personnel = user_Personnel;
    }

    public String getUser_Blog() {
        return User_Blog;
    }

    public void setUser_Blog(String user_Blog) {
        User_Blog = user_Blog;
    }

    public String getUser_Motive() {
        return User_Motive;
    }

    public void setUser_Motive(String user_Motive) {
        User_Motive = user_Motive;
    }
}
