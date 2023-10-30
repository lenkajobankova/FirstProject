package com.engeto.restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Cookbook {
    List<Dish> cookbook = new ArrayList<>();

    //region Constructor

    public Cookbook(List<Dish> cookbook) {
        this.cookbook.addAll(cookbook);
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
        this.cookbook.addAll(cookbook);
    }

    //endregion

    public static void saveToFile(String filename, List<Dish> cookbook) throws RestaurantException {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(Settings.fileNameCookbook())))) {
            for (Dish dish : cookbook){
                writer.println(dish.getTitle()+Settings.fileNameCookbook()+dish.getPrice()+Settings.delimiter()+
                        dish.getPreparationTime()+Settings.delimiter()+dish.getImage());
            }
        } catch (IOException e) {
            throw new RestaurantException("Nastala chyba při zápisu do souboru "+
                    Settings.fileNameCookbook()+e.getLocalizedMessage());
        }
    }
}
