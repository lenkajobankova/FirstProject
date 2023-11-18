package com.engeto.restaurant;

import java.io.*;
import java.util.*;

public class Cookbook {
    private static List<Dish> cookbook = new ArrayList<>();
    private static Map<Integer, Dish> dishes = new HashMap<>();

    //region Constructor
    public Cookbook(){};

    //endregion

    //region Methods
    public static void addDish(Dish dish){
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
        int lineNumber = 1;
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
                addDish(newDish);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new RestaurantException("Nastala chyba při čtení ze souboru "+
                    filename +e.getLocalizedMessage());
        }
        getListToMap(cookbook, dishes);
    }
    public static void getListToMap(List<Dish> cookbook, Map<Integer, Dish> dishes){
        for (Dish dish : cookbook){
            dishes.put(dish.getId(), dish);
        }
    }
    public static Dish getDishById(int id){
        Dish result = dishes.get(id);
        return result;
    }

    @Override
    public String toString() {
        return "Seznam jídel: " + cookbook;
    }
    //endregion
}
