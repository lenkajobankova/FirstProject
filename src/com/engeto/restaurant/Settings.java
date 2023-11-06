package com.engeto.restaurant;

import java.time.LocalTime;

public class Settings {
    private static final String FILE_NAME_COOKBOOK = "cookbook.txt";
    private static final String FILE_NAME_ORDERS = "listOfOrders.txt";
    private static final String DELIMITER = ";";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final LocalTime CLOSING_TIME = LocalTime.of(0, 0);

    public static String fileNameCookbook(){
        return FILE_NAME_COOKBOOK;
    }
    public static String fileNameOrders(){
        return FILE_NAME_ORDERS;
    }
    public static String delimiter(){
        return DELIMITER;
    }
    public static String tab(){
        return TAB;
    }
    public static String space(){
        return SPACE;
    }
    public static LocalTime closingTime(){
        return CLOSING_TIME;
    }
}
