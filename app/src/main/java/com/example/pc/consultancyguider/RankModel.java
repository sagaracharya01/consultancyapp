package com.example.pc.consultancyguider;

public class RankModel {
    private String cname;
    private String point;

    public RankModel(String cname, String point) {
        this.cname = cname;
        this.point = point;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
