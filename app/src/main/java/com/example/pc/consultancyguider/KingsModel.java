package com.example.pc.consultancyguider;

public class KingsModel {

    private String subject,desc;

    public KingsModel(String subject, String desc) {
        this.subject = subject;
        this.desc = desc;
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
