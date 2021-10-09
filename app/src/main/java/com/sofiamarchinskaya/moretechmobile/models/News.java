package com.sofiamarchinskaya.moretechmobile.models;

public class News {
    private String text;
    private String type;
    private String titleCompany;

    public String getTitleCompany() {
        return titleCompany;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public News(String text, String type, String titleCompany) {
        this.text = text;
        this.type = type;
        this.titleCompany = titleCompany;
    }
}
