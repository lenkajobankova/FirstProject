package com.engeto.restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cookbook {
    List<Dish> cookbook = new ArrayList<>();

    //region Constructor
    public Cookbook(){};

    //endregion

    //region Methods
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

    public static void saveToFile(String filename, List<Dish> cookbook) throws RestaurantException {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : cookbook){
                writer.println(dish.getId()+Settings.delimiter()+
                        dish.getTitle()+Settings.delimiter()+
                        dish.getPrice()+Settings.delimiter()+
                        dish.getPreparationTime()+Settings.delimiter()+
                        dish.getImage());
            }
        } catch (IOException e) {
            throw new RestaurantException("Nastala chyba při zápisu do souboru "+
                    filename+e.getLocalizedMessage());
        }
    }
    public static void loadFromFile(String filename) throws RestaurantException {
        Cookbook result = new Cookbook();
        int lineNumber = 1;
        loadFromFileScanner(filename, lineNumber, result);
    }

    private static void loadFromFileScanner(String filename, int lineNumber, Cookbook result) throws RestaurantException {
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] blocks = line.split(Settings.delimiter());
                int numOfBlocks = blocks.length;
                if(numOfBlocks != 5){
                    throw new RestaurantException("Špatně zadaný počet položek na řádku č. "+ lineNumber +
                            " ("+line+"), zadáno: "+numOfBlocks+" položek.");
                }
                Dish newDish = new Dish(blocks);
                result.addDish(newDish);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new RestaurantException("Nastala chyba při čtení ze souboru "+
                    filename +e.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return "Seznam jídel: " + cookbook;
    }
    //endregion
}
