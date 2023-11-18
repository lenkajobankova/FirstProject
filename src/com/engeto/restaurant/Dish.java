package com.engeto.restaurant;

import java.math.BigDecimal;

public class Dish {
    private int id = nextId;
    private static int nextId = 1;
    private String title;
    private String amount;
    private BigDecimal price;
    private int preparationTime;
    private String image;

    //region Constructor
    public Dish(String title, String amount, BigDecimal price, int preparationTime, String image) {
        this.id = nextId++;
        this.title = title;
        this.amount = amount;
        this.price = price;
        this.preparationTime = preparationTime;
        this.image = image;
    }
    public Dish(String title, String amount, BigDecimal price, int preparationTime) {
        this(title, amount, price, preparationTime, "blank");
        this.id = nextId++;
    }
    public Dish(String[] blocks) throws RestaurantException {
        setId(Integer.parseInt(blocks[0].trim()));
        this.title = blocks[1].trim();
        setPrice(new BigDecimal(blocks[2].trim()));
        setPreparationTime(Integer.parseInt(blocks[3].trim()));
        this.image = blocks[4].trim();
    }
    //endregion

    //region Methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws RestaurantException {
        try {
            if (id<=nextId) this.id =nextId++;
            if (id<1){
                throw new RestaurantException("Zadané číslo jídla musí být větší než nula!" +
                        " Zadáno: "+id);
            }
        } catch (NumberFormatException e) {
            throw new RestaurantException("Špatně zadaný formát čísla!"+e.getLocalizedMessage());
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws RestaurantException {
        try {
            this.price = price;
            if (price.compareTo(BigDecimal.valueOf(0))<1){
                throw new RestaurantException("Cena nemůže být menší než 1! Zadáno:"+price);
            }
        } catch (NumberFormatException e) {
            throw new RestaurantException("Chybně zadaný formát čísla! "+
                    e.getLocalizedMessage());
        }
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) throws RestaurantException {
        try{
            this.preparationTime = preparationTime;
            if (preparationTime<=0){
                throw new RestaurantException("Doba přípravy nesmí být záporné číslo " +
                        "nebo nula! Bylo zadáno "+preparationTime+" minut.");
            }
        } catch (NumberFormatException e) {
            throw new RestaurantException("Nesprávně zadaný formát čísla "+
                    e.getLocalizedMessage());
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return  "\n" + id +
                ". " + title +
                ", cena: " + price +
                " Kč, čas přípravy: " + preparationTime +
                " minut, obrázek: " + image;
    }
    //endregion
}
