package com.engeto.restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ListOfOrders {
    private List<Order> listOfOrders = new ArrayList<>();

    public ListOfOrders(List<Order> listOfOrders) {
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
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(Settings.fileNameOrders())))){
            for (Order order : listOfOrders){
                writer.println(order.getTableNumber()+Settings.delimiter()+
                        order.getDish().getId()+Settings.delimiter()+
                        order.getOrderedTime()+Settings.delimiter()+
                        order.getFulfilmentTime()+Settings.delimiter()+
                        order.isPaid());
            }
        } catch (IOException e) {
            throw new RestaurantException("Nastala chyba při zápisu do souboru "+
                    Settings.fileNameOrders()+e.getLocalizedMessage());
        }
    }
}
