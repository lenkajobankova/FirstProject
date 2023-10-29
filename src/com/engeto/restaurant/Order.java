package com.engeto.restaurant;

import java.time.LocalTime;

public class Order {
    private int tableNumber;
    private Dish dish;
    private LocalTime orderedTime;
    private LocalTime fulfilmentTime;
    private boolean isPaid;

    //region Constructors

    public Order(int tableNumber, Dish dish, LocalTime orderedTime, LocalTime fulfilmentTime, boolean isPaid) {
        this.tableNumber = tableNumber;
        this.dish = dish;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.isPaid = isPaid;
    }

    //endregion

    //region Getters and Setters

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public LocalTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    //endregion
}
