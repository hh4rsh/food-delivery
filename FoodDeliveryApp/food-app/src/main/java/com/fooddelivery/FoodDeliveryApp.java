package com.fooddelivery;
import org.jetbrains.annotations.NotNull;

import java.util.*;
//user class
class User{
    private String username;
    private String password;
    public User(String username,String password){

        this.username=username;
        this.password=password;
        System.out.println("\nThank you" + username + "for signing up ☺");
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
//food item
class FoodItem {
    private int id;
    private String name;
    private double price;
    public FoodItem(int id,String name,double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public void add(@NotNull FoodItem item) {
    }
}
//Cart
class Cart{
    private List<FoodItem> items = new ArrayList<>();
    public void addItem(FoodItem item){

        items.add(item);
        System.out.println(item.getName() + " add to cart");
    }
    public void showCart(){

        System.out.println("\nCart");
        double total = 0;
        for(FoodItem item : items){
            System.out.println(item.getId() + ". "
                    + item.getName()
                    + " - ₹" + item.getPrice());

            total += item.getPrice();
        }
        System.out.println("Total amount: ₹" + total);
    }

    public double getTotal(){
        double total = 0;

        for (FoodItem item : items){
            total += item.getPrice();
        }
        return total;
    }
}
//payment
interface Payment{
    void pay(double amount);
}
class onlinePayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("\nOnline payment successful: ₹" + amount);
    }
}
class cardPayment implements Payment{
    @Override
    public void pay(double amount){
        System.out.println("\nCard payment successful: ₹" + amount);
    }
}
//delivery
class DeliveryThread extends Thread{
    @Override
    public void run(){
        try{
            System.out.println("\n Preparing your order");
            Thread.sleep(2000);
            System.out.println("delivery start");
            Thread.sleep(3000);
            System.out.println("Order delivery Successfully");
        }
        catch (Exception e){
            System.out.println("delivery failed");
        }
    }
}
//main class
class FoodDeliveryApp{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Sign up");

        System.out.print("Choose username: ");
        String username = sc.nextLine();
        System.out.print("Choose password: ");
        String password = sc.nextLine();
         User user = new User(username,password);
        System.out.println("login");

        System.out.print("Enter user:_");
        String loginUser = sc.nextLine();
        System.out.print("Enter password");
        String loginPass = sc.nextLine();

        if (user.getUsername().equals(loginUser)
                && user.getPassword().equals(loginPass)) {
            System.out.println("login Successful");
        } else {
            System.out.println("Invalid Username or Password");
            return;
        }
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem(1, "Burger", 60));
        menu.add(new FoodItem(2, "Pizza", 120));
        menu.add(new FoodItem(3, "Pasta", 100));
        menu.add(new FoodItem(4, "Grilled chicken", 40));
        menu.add(new FoodItem(5, "Biryani", 40));
        menu.add(new FoodItem(6, "Butter chicken", 40));
        menu.add(new FoodItem(7, "Paneer butter", 40));
        menu.add(new FoodItem(8, "Noodles", 40));
        menu.add(new FoodItem(9, "Fried rice", 40));
        menu.add(new FoodItem(10, "Green Salad", 40));
        Cart cart = new Cart();
        while(true){
            System.out.println("Food Menu");
            for (FoodItem item : menu) {

                System.out.println(item.getId() + ". "
                        + item.getName()
                        + " - ₹" + item.getPrice());
            }

            System.out.println("0. Checkout");
            System.out.print("Select food: ");
            int choice = sc.nextInt();

            if (choice == 0) {
                break;
            }

            boolean found = false;

            for (FoodItem item : menu) {

                if (item.getId() == choice) {
                    cart.addItem(item);
                    found = true;
                    break;
                }
            }
            if (!found) {

                System.out.println("Invalid Item");
            }
        }

        cart.showCart();
        System.out.println("\nPayment");
        System.out.println("1. online");
        System.out.println("2. Card");
        System.out.print("Choose Payment Method: ");
        int paymentChoice = sc.nextInt();
        Payment payment;

        if (paymentChoice == 1) {
            payment = new onlinePayment();

        } else {
            payment = new cardPayment();
        }

        payment.pay(cart.getTotal());

        DeliveryThread delivery = new DeliveryThread();
        delivery.start();
        sc.close();
    }
}

