package application.project4_pizzaordermanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class PepperoniPriceTest {

    @Test
    public void price() {
        Pepperoni pepperoni1 = new Pepperoni(Size.Small); // Small size pepperoni pizza with 1 topping.
        Pepperoni pepperoni2 = new Pepperoni(Size.Small); // Small size pepperoni pizza with 2 toppings.
        Pepperoni pepperoni3 = new Pepperoni(Size.Small); // Small size pepperoni pizza with 7 toppings.
        Pepperoni pepperoni4 = new Pepperoni(Size.Medium); // Medium size pepperoni pizza with 1 topping.
        Pepperoni pepperoni5 = new Pepperoni(Size.Medium); // Medium size pepperoni pizza with 8 toppings.
        Pepperoni pepperoni6 = new Pepperoni(Size.Large); // Large size pepperoni pizza with 1 topping.
        Pepperoni pepperoni7 = new Pepperoni(Size.Large); // Large size pepperoni pizza with 0 topping.

        pepperoni2.toppings.add(Topping.GreenPepper);
        pepperoni3.toppings.add(Topping.Pineapple);
        pepperoni3.toppings.add(Topping.Sausage);
        pepperoni3.toppings.add(Topping.Cheese);
        pepperoni3.toppings.add(Topping.Ham);
        pepperoni3.toppings.add(Topping.Mushroom);
        pepperoni3.toppings.add(Topping.Onion);
        pepperoni5.toppings.add(Topping.Pineapple);
        pepperoni5.toppings.add(Topping.Sausage);
        pepperoni5.toppings.add(Topping.Cheese);
        pepperoni5.toppings.add(Topping.Ham);
        pepperoni5.toppings.add(Topping.Mushroom);
        pepperoni5.toppings.add(Topping.Onion);
        pepperoni5.toppings.add(Topping.Beef);

        assertTrue(pepperoni1.price() == 8.99);
        assertTrue(pepperoni2.price() == 10.48);
        assertTrue(pepperoni3.price() == 17.93);
        assertTrue(pepperoni4.price() == 10.99);
        assertTrue(pepperoni6.price() == 12.99);
        assertFalse(Double.isNaN(pepperoni5.price()));
        assertFalse(Double.isNaN(pepperoni7.price()));
    }
}