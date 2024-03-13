package com.example.vk.internship.model;

public class Company {
    private String companyName;
    private String catchPhrase;
    private String bs;

    public Company() {
    }

    public Company(String name, String catchPhrase, String bs) {
        this.companyName = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return companyName;
    }

    public void setName(String name) {
        this.companyName = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
