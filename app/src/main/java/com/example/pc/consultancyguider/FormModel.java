package com.example.pc.consultancyguider;

public class FormModel {
    private String name;
    private String address,email,phone,gender,education,consultancy;

    public FormModel(String name, String address, String email, String phone, String gender, String education, String consultancy) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.education = education;
        this.consultancy = consultancy;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getConsultancy() {
        return consultancy;
    }

    public void setConsultancy(String consultancy) {
        this.consultancy = consultancy;
    }
}
