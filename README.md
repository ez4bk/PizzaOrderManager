# PizzaOrderManager
A software for the Pizzeria to manage the orders. Use JavaFX to develop the GUIs for taking the orders, placing the orders, and cancelling an order.\
The software has 4 GUI views and their corresponding controllers, Main, Pizza, Current Order, and Store Order.\
Pizza is an abstract class, and 3 flavors of pizzas are sub-classes extended from Pizza.

Currently, the Pizzeria offers 3 flavors of pizzas, Deluxe, Hawaiian and Pepperoni, with store customized toppings. However, 
the Pizzeria allows the customers to customize the toppings as well. The store staff will be the one using the software to 
take the orders from the customers.\
Each order is uniquely identified by the customer’s 10-digit telephone number. \
For simplicity, the system will not keep track of the dates of the orders.

The store manager has given you the list of requirements as follows. 
1. The Pizzeria offers 3 different sizes of pizzas, small, medium, or large, for each flavor.
   - Deluxe pizza includes 5 toppings, the price for a small size is $12.99.
   - Hawaiian pizza includes 2 toppings, the price for a small size is $10.99.
   - Pepperoni pizza includes 1 topping, the price for a small size is $8.99.
   - Add $2.00 for each size increase
   - Add $1.49 for each additional topping, up to 7 toppings maximum.
   - Pizza prices do not decrease if the number of toppings dropped below the number of store customized toppings.
2. The system allows the store staff to choose the pizza flavors with different sizes.
3. Upon the selection of the pizza flavor, the system displays the image of the selected pizza, the list of stored customized toppings, 
a list of available toppings for customization, and a list of sizes to choose from.
4. The store staff shall be able to customize the selected pizza by adding or removing the toppings and the system 
shall display the running subtotal with 2 decimal places.
5. The system shall allow the store staff to add multiple pizzas to the same order and remove selected pizzas from 
the order.
6. The store staff shall be able to check the detail of the current order before placing the order. These shall include
the list of pizzas with the selected toppings, subtotal for each pizza, total amount of the pizzas in the order, sales 
tax amount and the order total, which is the total amount plus sales tax. The tax rate is 6.625%.
7. The system shall be able to keep track of all the store orders, allow the store staff to browse the store orders and 
cancel an order. These shall include displaying all the store orders by the customers’ phone numbers, the order 
total for each order with 2 decimal places, and the list of pizzas in each order.
8. The system shall be able to export the store orders and save them in a text file, which includes a list of store 
orders. Each store order shall include the customer’s phone number, the list of pizzas with selected toppings and 
the order total.

# Main View
Create a new order with customer's phone number add pizzas to it or place it to the order list.\
Three buttons of pizzas with corresponding images are set to open up the Pizza view for cusomization.\
A Current Order button is set to open up the Curren Order view for showing order details and order manipulation.\
A Store Order button is set to open up the Store Order view for showing and exporting the order list or removing an order from the list.

# Pizza View
Customize the pizza. Display the default set-up of the pizza depending on which pizza button is clicked in Main.\
Choose size, add or remove toppings from the pizza, show the price spontaneously, and add the customized pizza to the order.\
The view will remain open after the pizza is added to the order. A new pizza of present customization is created for adding duplicated pizzas.

# Current Order View
Show the temporary order details including the customer's phone number and the list of pizzas held in the order.\
Remove a pizza from the order.\
Place the order to the order list\
The view is closed automatically if all pizzas are removed from the order.\
If the order is empty, the view will not show up.

# Store Order View
Show the order detail on select in the list of order placed so far.\
Choose which order to show.\
Remove the selected order from the order list.\
Export the order list to a text file to a location on user's selection. (Currently problematic)\
The view is closed automatically if all orders are removed from the order list.\
If the order list is empty, the view will not show up.
