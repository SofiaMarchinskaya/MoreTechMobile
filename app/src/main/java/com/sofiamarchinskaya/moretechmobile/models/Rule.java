package com.sofiamarchinskaya.moretechmobile.models;


public class Rule {
    private String ruleName; // название
    private int image; // ресурс

    public Rule(String ruleName, int image){

        this.image=image;
        this.ruleName=ruleName;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }


    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
