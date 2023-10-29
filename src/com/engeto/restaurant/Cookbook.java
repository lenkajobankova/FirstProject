package com.engeto.restaurant;

import java.util.ArrayList;
import java.util.List;

public class Cookbook {
    List<Dish> cookbook = new ArrayList<>();

    //region Constructor

    public Cookbook(List<Dish> cookbook) {
        this.cookbook = cookbook;
    }

    //endregion

    //region Getters and Setters
    public void addDish(Dish dish){
        cookbook.add(dish);
    }
    public void removeDish(Dish dish){
        cookbook.remove(dish);
    }
    public void removeDish(int index){
        cookbook.remove(index);
    }
    public List<Dish> getCookbook() {
        return new ArrayList<>(cookbook);
    }

    public void setCookbook(List<Dish> cookbook) {
        this.cookbook = cookbook;
    }

    //endregion
}
