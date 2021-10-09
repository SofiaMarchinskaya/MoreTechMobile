package com.sofiamarchinskaya.moretechmobile;

public class BudgetCard {
    private final int image;
    private final String title;
    private final int Description;
    private final int price;

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getDescription() {
        return Description;
    }

    public int getPrice() {
        return price;
    }

    public int getHappy_percent() {
        return happy_percent;
    }

    private final int happy_percent;
    BudgetCard(int image, String title,int description, int price, int happy_percent){

        this.image = image;
        this.title = title;
        this.Description = description;
        this.price = price;
        this.happy_percent = happy_percent;
    }


}
