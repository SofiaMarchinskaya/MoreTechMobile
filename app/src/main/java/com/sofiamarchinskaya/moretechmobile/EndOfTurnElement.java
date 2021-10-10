package com.sofiamarchinskaya.moretechmobile;

public class EndOfTurnElement {
    private String title; // название
    private int image; // ресурс
    private String summary;

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public EndOfTurnElement(String Name, int image, String summary){

        this.image=image;
        this.title=Name;
        this.summary = summary;
    }


}
