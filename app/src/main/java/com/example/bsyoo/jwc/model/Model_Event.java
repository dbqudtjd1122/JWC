package com.example.bsyoo.jwc.model;

public class Model_Event {

    private String event_name;
    private String event_time;
    private Integer img;

    @Override
    public String toString() {
        return "Model_Event{" +
                "event_name='" + event_name + '\'' +
                ", event_time=" + event_time +
                ", img=" + img +
                '}';
    }

    public Model_Event() {
    }

    public Model_Event(String event_name, String time, Integer img) {
        this.event_name = event_name;
        this.event_time = time;
        this.img = img;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
