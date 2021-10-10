package com.sofiamarchinskaya.moretechmobile.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class Company {
    private String title;
    private String subtitle;
    private String description;
    private int deposit;
    private int imageResources;
    private List<Company> affiliatedСompanies;
    private boolean isUpPrice;
    private SharedPreferences preferences;

    public Company(String title, String subtitle, String description, int deposit,
                   int imageResources,
                   List<Company> affiliatedСompanies, Context context) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.deposit = deposit;
        this.imageResources = imageResources;
        this.affiliatedСompanies = affiliatedСompanies;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(title+"first", deposit).apply();
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getImageResources() {
        return imageResources;
    }

    public List<Company> getAffiliatedCompanies() {
        return affiliatedСompanies;
    }

    public boolean isUpPrice() {
        return isUpPrice;
    }

    public void setUpPrice(boolean upPrice) {
        preferences.edit().putBoolean(title+"isUpPrice", upPrice).apply();
        isUpPrice = upPrice;
    }
}
