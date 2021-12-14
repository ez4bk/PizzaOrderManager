package application.project4_pizzaordermanager;

/**
 * An object class that represents Deluxe pizzas. Sub-class extended from Pizza
 * abstract class.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class Deluxe extends Pizza {

    private static final int MINTOPPINGS = 5;
    private static final double DEFAULT_PRIZE = 12.99;

    /**
     * Constructor of Deluxe Object.
     * 
     * @param size the size of deluxe pizza.
     */
    public Deluxe(Size size) {
        super(size);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.GreenPepper);
        toppings.add(Topping.Mushroom);
        toppings.add(Topping.Sausage);
    }

    /**
     * An override method that computes the price of deluxe pizza with toppings
     * added or removed.
     * 
     * @return the computed price of deluxe pizza.
     */
    @Override
    public double price() {
        double price = DEFAULT_PRIZE;
        price += (toppings.size() - MINTOPPINGS) * PRICEPERTOPPING + this.size.ordinal() * PRICEPERSIZE;
        return price;
    }

    /**
     * An override method that returns the minimum toppings a deluxe pizza can have.
     * 
     * @return the minimum toppings for deluxe pizza.
     */
    @Override
    public int getMinTopping() {
        return MINTOPPINGS;
    }
}
