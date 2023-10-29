package com.engeto.restaurant;

import java.math.BigDecimal;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private String image;

    //region Constructor

    public Dish(String title, BigDecimal price, int preparationTime, String image) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.image = image;
    }
    public Dish(String title, BigDecimal price, int preparationTime) {
        this(title, price, preparationTime, "blank");
    }

    //endregion

    //region Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        if (preparationTime>0){
            this.preparationTime = preparationTime;
        } else {
            System.err.println("Doba přípravy nesmí být záporné číslo nebo nula!");
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //endregion
}
