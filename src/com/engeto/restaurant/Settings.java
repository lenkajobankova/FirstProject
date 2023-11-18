package com.engeto.restaurant;

import java.time.LocalTime;

public class Settings {
    private static final String FILE_NAME_COOKBOOK = "cookbook.txt";
    private static final String FILE_NAME_ORDERS = "listOfOrders.txt";
    private static final String DELIMITER = ";";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final String DOUBLE_ASTERISK = "**";
    private static final String ORDER_START_DELIMITER = "****";
    private static final String ORDER_END_DELIMITER = "******";

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
    public static String doubleAsterisk(){
        return DOUBLE_ASTERISK;
    }
    public static String orderStartDelimiter(){
        return ORDER_START_DELIMITER;
    }
    public static String orderEndDelimiter(){
        return ORDER_END_DELIMITER;
    }
}
