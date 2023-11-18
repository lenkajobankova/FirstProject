import com.engeto.restaurant.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

//Uživatelské jméno na Discordu: Lenka B.

public class Main {
    public static void main(String[] args) {
        Cookbook cookbook = new Cookbook();
        ListOfOrders orders = new ListOfOrders();

        // Úkol č. 1
        cookbookLoadFromFile();
        ordersLoadFromFile();

        // Úkol č. 2
        setCookbook(cookbook);
        setOrders(orders, cookbook);

        // Úkoly č. 3-4
        controlPrints(orders);

        // Úkol č. 5
        cookbookSaveToFile(cookbook);
        ordersSaveToFile(orders);

        // Úkol č. 6
        cookbookLoadFromFile();
        ordersLoadFromFile();
    }

    private static void controlPrints(ListOfOrders orders) {
        System.out.print("Celková cena pro stůl č. 15: ");
        System.out.println(RestaurantManager.getTotalPriceForTable(15, orders.getListOfOrders())+" Kč.");

        System.out.print("Počet nezaplacených objednávek: ");
        System.out.println(RestaurantManager.getUnpaidOrders(orders.getListOfOrders()));

        System.out.print("Počet nedokončených objednávek: ");
        System.out.println(RestaurantManager.getUnfinishedOrders(orders.getListOfOrders()));

        System.out.println("Objednávky seřazené podle času zadání:");
        System.out.println(RestaurantManager.getListByOrderedTime(orders.getListOfOrders()));

        System.out.print("Průměrná doba zpracování objednávek: ");
        System.out.println(RestaurantManager.getAverageProcessingTime(orders.getListOfOrders())+" minut na objednávku");

        System.out.println("Seznam dnes objednaných jídel:");
        System.out.println(RestaurantManager.getListOfTodaysOrders(orders.getListOfOrders()));

        System.out.println("Výpis objednávek pro jeden stůl:");
        RestaurantManager.getOneTableOrders(15, orders.getListOfOrders());
    }

    private static void setOrders(ListOfOrders orders, Cookbook cookbook) {
        orders.setListOfOrders(List.of(
                new Order(15, cookbook.getCookbook().get(0),2,
                        LocalTime.of(15, 20), false),
                new Order(15, cookbook.getCookbook().get(1), 2,
                        LocalTime.of(15,20), false),
                new Order(15, cookbook.getCookbook().get(3), 2,
                        LocalTime.of(15,20), LocalTime.of(15,30), false),
                new Order(2, cookbook.getCookbook().get(2), 2,
                        LocalTime.of(16, 40), LocalTime.of(17, 0), true)
        ));
    }

    private static void setCookbook(Cookbook cookbook) {
        cookbook.setCookbook(List.of(
                new Dish("Kuřecí řízek obalovaný", "150g",
                        BigDecimal.valueOf(69), 20, "rizek"),
                new Dish("Hranolky", "150g",
                        BigDecimal.valueOf(45), 10, "hranolky"),
                new Dish("Pstruh na víně", "200g",
                        BigDecimal.valueOf(169), 20, "pstruh"),
                new Dish("Kofola","0.5l",
                        BigDecimal.valueOf(39), 5, "kofola")));
    }

    private static void cookbookLoadFromFile() {
        try{
            Cookbook.loadFromFile(Settings.fileNameCookbook());
        } catch (NumberFormatException | RestaurantException e) {
            System.out.println("Soubor pro načtení dříve uložených jídel nebyl nalezen.");
        }
    }

    private static void cookbookSaveToFile(Cookbook cookbook) {
        try{
            Cookbook.saveToFile(Settings.fileNameCookbook(), cookbook.getCookbook());
        } catch (RestaurantException e) {
            System.err.println("Nastala chyba při zápisu do souboru "+e.getLocalizedMessage());
        }
    }
    private static void ordersLoadFromFile() {
        try{
            ListOfOrders.loadFromFile(Settings.fileNameOrders());
        } catch (RestaurantException | DateTimeParseException e) {
            System.out.println("Soubor pro načtení předchozích objednávek nebyl nalezen.");
        }
    }

    private static void ordersSaveToFile(ListOfOrders orders) {
        try{
            ListOfOrders.saveToFile(Settings.fileNameOrders(), orders.getListOfOrders());
        } catch (RestaurantException e) {
            System.err.println("Nastala chyba při zápisu do souboru "+e.getLocalizedMessage());
        }
    }
}