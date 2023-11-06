package com.engeto.restaurant;

import java.time.LocalTime;

public class Order implements Comparable<Order>{
    private int tableNumber;
    private Dish dish;
    private int dishId;
    private int dishAmount;
    private LocalTime orderedTime;
    private LocalTime fulfilmentTime;
    private boolean isPaid;

    //region Constructors
    public Order(int tableNumber, Dish dish, int dishAmount, LocalTime orderedTime, boolean isPaid){
        this.tableNumber = tableNumber;
        this.dish = dish;
        this.dishAmount = dishAmount;
        this.orderedTime = orderedTime;
        this.isPaid = isPaid;
    }
    public Order(int tableNumber, Dish dish, int dishAmount, LocalTime orderedTime, LocalTime fulfilmentTime, boolean isPaid) {
        this(tableNumber, dish, dishAmount, orderedTime, isPaid);
        this.fulfilmentTime = fulfilmentTime;
    }

    public Order(String[] blocks) throws RestaurantException {
        setTableNumber(Integer.parseInt(blocks[0].trim()));
        this.dishId = Integer.parseInt(blocks[1].trim());
        setOrderedTime(LocalTime.parse(blocks[2].trim()));
        setFulfilmentTime(LocalTime.parse(blocks[3].trim()));
        this.isPaid = Boolean.parseBoolean(blocks[4]);
    }

    //endregion

    //region Methods

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) throws RestaurantException {
        try {
            this.tableNumber = tableNumber;
            if (tableNumber<1){
                throw new RestaurantException("Zadané číslo stolu musí být větší než nula!" +
                        " Zadáno: "+tableNumber);
            }
        } catch (NumberFormatException e) {
            throw new RestaurantException("Špatně zadaný formát čísla!"+e.getLocalizedMessage());
        }
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    public LocalTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalTime getFulfilmentTime() { // dokud nebude zadán čas vyřízení, zapíše se čas, kdy restaurace zavírá
        if (fulfilmentTime != null){
            return fulfilmentTime;
        } else {
            return Settings.closingTime();
        }
    }

    public void setFulfilmentTime(LocalTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public boolean isPaid() {
        return isPaid;
    }
    public String isPaidAsText() {
        String paid = "zaplaceno";
        String unPaid = " ";
        if (isPaid){
            return paid;
        }
        return unPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public int compareTo(Order otherOrder) {
        return orderedTime.compareTo(otherOrder.getOrderedTime());
    }

    @Override
    public String toString() {
        return  "Stůl č." + tableNumber +
                " " + dish.getTitle() +
                " " + dishAmount +
                "x, čas objednání: " + orderedTime +
                ", " + isPaidAsText() +
                "\n";
    }
    //endregion
}
