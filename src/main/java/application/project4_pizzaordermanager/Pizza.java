package application.project4_pizzaordermanager;

import java.util.ArrayList;

/**
 * Abstract class of Pizza.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public abstract class Pizza {

    public static final double PRICEPERSIZE = 2.00;
    public static final double PRICEPERTOPPING = 1.49;

    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    /**
     * Constructor of Pepperoni Object.
     * 
     * @param size the size of pepperoni pizza.
     */
    public Pizza(Size size) {
        this.size = size;
    }

    /**
     * @return the String that contains a pizza's information.
     */
    @Override
    public String toString() {
        String info = "";
        if (this instanceof Deluxe) {
            info += "Deluxe";
        } else if (this instanceof Hawaiian) {
            info += "Hawaiian";
        } else if (this instanceof Pepperoni) {
            info += "Pepperoni";
        }
        info += " pizza,";
        for (Topping t : this.toppings) {
            info += (" " + t + ",");
        }
        info += (" " + size + ",");
        info += (" $" + String.format("%.2f", this.price()));
        return info;
    }

    /**
     * @return minimum number of toppings for each pizza.
     */
    public abstract int getMinTopping();

    /**
     * @return the price calculated of each pizza.
     */
    public abstract double price();

}
