package com.example.bsyoo.jwc.model;

public class ModelCompany {

    private String Team_name;
    private String Team_Title;
    private Integer Img_Company;
    private Integer Img_Work;
    private String Tv_Company;


    @Override
    public String toString() {
        return "ModelCompany{" +
                "Team_name='" + Team_name + '\'' +
                ", Team_Title='" + Team_Title + '\'' +
                ", Img_Company=" + Img_Company +
                ", Img_Work=" + Img_Work +
                ", Tv_Company='" + Tv_Company + '\'' +
                '}';
    }

    public ModelCompany(String team_name, String team_Title, Integer img_Company, Integer img_Work, String tv_Company) {
        Team_name = team_name;
        Team_Title = team_Title;
        Img_Company = img_Company;
        Img_Work = img_Work;
        Tv_Company = tv_Company;
    }

    public ModelCompany() {
    }

    public Integer getImg_Work() {
        return Img_Work;
    }

    public void setImg_Work(Integer img_Work) {
        Img_Work = img_Work;
    }

    public String getTeam_Title() {
        return Team_Title;
    }

    public void setTeam_Title(String team_Title) {
        Team_Title = team_Title;
    }

    public String getTeam_name() {
        return Team_name;
    }

    public void setTeam_name(String team_name) {
        Team_name = team_name;
    }

    public Integer getImg_Company() {
        return Img_Company;
    }

    public void setImg_Company(Integer img_Company) {
        Img_Company = img_Company;
    }

    public String getTv_Company() {
        return Tv_Company;
    }

    public void setTv_Company(String tv_Company) {
        Tv_Company = tv_Company;
    }
}
