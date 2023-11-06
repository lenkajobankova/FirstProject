package com.engeto.restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfOrders {
    private final List<Order> listOfOrders = new ArrayList<>();
    //region Constructor
    public ListOfOrders(){};
    //endregion
    //region Methods
    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders.addAll(listOfOrders);
    }
    public void addOrder(Order order){
        listOfOrders.add(order);
    }
    public void removeOrder(Order order){
        listOfOrders.remove(order);
    }
    public void removeOrder(int index){
        listOfOrders.remove(index);
    }
    public List<Order> getListOfOrders(){
        return new ArrayList<>(listOfOrders);
    }
    public static void saveToFile(String filename, List<Order> listOfOrders) throws RestaurantException {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Order order : listOfOrders){
                writer.println(order.getTableNumber()+Settings.delimiter()+
                        order.getDish().getId()+Settings.delimiter()+
                        order.getOrderedTime()+Settings.delimiter()+
                        order.getFulfilmentTime()+Settings.delimiter()+
                        order.isPaid());
            }
        } catch (IOException e) {
            throw new RestaurantException("Nastala chyba při zápisu do souboru "+
                    filename+e.getLocalizedMessage());
        }
    }
    public static void loadFromFile(String filename) throws RestaurantException {
        ListOfOrders result = new ListOfOrders();
        int lineNumber = 1;
        loadFromFileScanner(filename, lineNumber, result);
    }

    private static void loadFromFileScanner(String filename, int lineNumber, ListOfOrders result) throws RestaurantException {
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] blocks = line.split(Settings.delimiter());
                int numOfBlocks = blocks.length;
                if(numOfBlocks != 5){
                    throw new RestaurantException("Špatně zadaný počet položek na řádku č. "+ lineNumber +
                            " ("+line+"), zadáno: "+numOfBlocks+" položek.");
                }
                Order newOrder = new Order(blocks);
                result.addOrder(newOrder);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new RestaurantException("Nastala chyba při čtení ze souboru "+
                    filename +e.getLocalizedMessage());
        }
    }
    //endregion

}
