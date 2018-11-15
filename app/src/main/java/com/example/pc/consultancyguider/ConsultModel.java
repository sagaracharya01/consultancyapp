package com.example.pc.consultancyguider;

public class ConsultModel {

    private String name;
    private String subject,desc;

    public ConsultModel(String name, String subject, String desc) {
        this.name = name;
        this.subject = subject;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
