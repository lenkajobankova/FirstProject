package com.engeto.restaurant;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RestaurantManager{
    //region Methods
    public static int getUnpaidOrders(List<Order> listOfOrders){
        int result = 0;
        for (Order order : listOfOrders){
            if (!order.isPaid()){
                result++;
            }
        }
        return result;
    }
    public static int getUnfinishedOrders(List<Order> listOfOrders){
        int result = 0;
            for(Order order : listOfOrders){
                if (order.getFulfilmentTime()==Settings.closingTime()){
                        result++;
                }
            }
        return result;
    }
    public static List<Order> getListByOrderedTime(List<Order> listOfOrders){
        List<Order> sortedList = new ArrayList<>(listOfOrders);
        Collections.sort(sortedList);
        return sortedList;
    }
    public static double getAverageProcessingTime(List<Order> listOfOrders){
        double result = 0;
        long minutes = 0;
        double finalResult;
        for (Order order : listOfOrders){
            if (order.getFulfilmentTime() != Settings.closingTime()) {
                minutes += ChronoUnit.MINUTES.between(order.getOrderedTime(),order.getFulfilmentTime());
            }
            result++;
        }
        finalResult = (double) minutes/result;
        return finalResult;
    }
    public static Set<Dish> getListOfTodaysOrders(List<Order> listOfOrders){
        Set<Dish> dishes = new HashSet<>();
        for (Order order : listOfOrders){
            dishes.add(order.getDish());
        }
        return dishes;
    }
    public static void getOneTableOrders(int tableNumber, List<Order> listOfOrders){
        getFormattedTableNumber(tableNumber);
        int orderNumber = 1;
        for (Order order : listOfOrders){
            if (order.getTableNumber() == tableNumber){
                System.out.println(orderNumber+"."+Settings.space()+
                        order.getDish().getTitle()+Settings.space()+
                        order.getDishAmount()+"x"+Settings.space()+"("+
                        (order.getDish().getPrice().multiply(BigDecimal.valueOf(order.getDishAmount())))+
                        " Kč"+")"+":"+Settings.tab()+order.getOrderedTime()+
                        getFulfilmentTimeNotNull(order.getFulfilmentTime())+Settings.tab()+
                        order.isPaidAsText());
            }
            orderNumber++;
        }
        System.out.println(Settings.orderEndDelimiter());
    }
    public static BigDecimal getTotalPriceForTable(int numberOfTable, List<Order> listOfOrders){
        BigDecimal totalprice = BigDecimal.valueOf(0);
        for (Order order : listOfOrders){
            if (order.getTableNumber() == numberOfTable){
                BigDecimal multiply = order.getDish().getPrice().multiply(BigDecimal.valueOf(order.getDishAmount()));
                totalprice = totalprice.add(multiply);
            }
        }
        return totalprice;
    }

    private static void getFormattedTableNumber(int tableNumber) {
        if (tableNumber <= 9){
            String tableNumberWithSpace = Settings.space() + tableNumber;
            System.out.println(Settings.doubleAsterisk()+Settings.space()+"Objednávky pro stůl č. "+
                    tableNumberWithSpace+Settings.space()+Settings.doubleAsterisk());
            System.out.println(Settings.orderStartDelimiter());
        } else {
            System.out.println(Settings.doubleAsterisk()+Settings.space()+"Objednávky pro stůl č. "+
                    tableNumber+Settings.space()+Settings.doubleAsterisk());
            System.out.println(Settings.orderStartDelimiter());
        }
    }
    private static String getFulfilmentTimeNotNull(LocalTime time){
        if (time != null){
            return "-"+String.valueOf(time);
        }
        return "";
    }
    //endregion
}
