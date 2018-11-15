package com.example.pc.consultancyguider;

public class ViewerModel {

    private String name, subject, desc, img_url;

    public ViewerModel(String name, String subject, String desc,String img_url) {
        this.name = name;
        this.subject = subject;
        this.desc = desc;
        this.img_url=img_url;
    }

    public String getImg_url() {
      return img_url;
    }

     public void setImg_url(String img_url) {
     this.img_url = img_url;
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
