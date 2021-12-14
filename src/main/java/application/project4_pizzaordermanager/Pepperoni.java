package application.project4_pizzaordermanager;

/**
 * An object class that represents Pepperoni pizzas. Sub-class extended from
 * Pizza abstract class.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class Pepperoni extends Pizza {

    private static final int MINTOPPINGS = 1;
    private static final double DEFAULT_PRIZE = 8.99;

    /**
     * Constructor of Pepperoni Object.
     * 
     * @param size the size of pepperoni pizza.
     */
    public Pepperoni(Size size) {
        super(size);
        toppings.add(Topping.Pepperoni);
    }

    /**
     * An override method that computes the price of pepperoni pizza with toppings
     * added or removed.
     * 
     * @return the computed price of pepperoni pizza.
     */
    @Override
    public double price() {
        double price = DEFAULT_PRIZE;
        price += (toppings.size() - MINTOPPINGS) * PRICEPERTOPPING + this.size.ordinal() * PRICEPERSIZE;
        return price;
    }

    /**
     * An override method that returns the minimum toppings a pepperoni pizza can
     * have.
     * 
     * @return the minimum toppings for pepperoni pizza.
     */
    @Override
    public int getMinTopping() {
        return MINTOPPINGS;
    }
}
