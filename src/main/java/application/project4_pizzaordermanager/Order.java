package application.project4_pizzaordermanager;

import java.util.ArrayList;

/**
 * Order Class that store customer's phone number and the pizza list he/she
 * orders.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class Order {

    private String orderNumber;
    private ArrayList<Pizza> pizzas;
    private static final double TAXRATE = 0.06625;

    /**
     * Constructor of Order Object.
     * 
     * @param orderNumber the customer number of the order.
     */
    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
        this.pizzas = new ArrayList<Pizza>();
    }

    /**
     * @return the order number of the given order.
     */
    public String getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * @return the arraylist holding the pizza.
     */
    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    /**
     * @return the subtotal price of the pizza in the given order.
     */
    public double getSubtotal() {
        double price = 0;
        for (Pizza p : pizzas) {
            price += p.price();
        }
        return price;
    }

    /**
     * @return the tax rate of the subtotal price of the pizza in the given order.
     */
    public double getTax() {
        return (this.getSubtotal() * TAXRATE);
    }

    /**
     * @return the total price of the pizza in the given order.
     */
    public double getOrderTotal() {
        return (this.getSubtotal() + this.getTax());
    }

    /**
     * Add the pizza into the arraylist holding pizza.
     * 
     * @param pizza the selected pizza.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Remove the pizza into the arraylist holding pizza.
     * 
     * @param pizza the selected pizza.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Check if two orders are the same by checking customer's phone number.
     * 
     * @param number the potential customer number of the order.
     * @return true if two orders are the same, false otherwise.
     */
    public boolean isSameOrder(String number) {
        return number.equals(this.orderNumber);
    }

}
