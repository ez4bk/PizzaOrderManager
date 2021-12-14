package application.project4_pizzaordermanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main Controller that handles ordering functions. Handles pizza selection,
 * customer phone number verification, and order manipulation.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class MainController {
    @FXML
    private ImageView DeluxeImage;
    @FXML
    private ImageView HawaiianImage;
    @FXML
    private ImageView PepperoniImage;
    @FXML
    private TextField phoneNumber;

    private Pizza pizza = null;
    private String pizzaName;
    private String customerNumber;
    private ArrayList<Order> placeOrders = new ArrayList<Order>();
    private Order order;

    /**
     * Share pizza data to other controllers.
     * 
     * @return the default pizza of the type selected.
     */
    protected Pizza getPizza() {
        return pizza;
    }

    /**
     * Share pizza's name data to other controllers.
     * 
     * @return pizza's of the type selected.
     */
    protected String getPizzaName() {
        return pizzaName;
    }

    /**
     * Share customer's phone humber to other controllers.
     * 
     * @return customer's phone number.
     */
    protected String getPhoneNumber() {
        return customerNumber;
    }

    /**
     * Share the image of the pizza selected to other controllers.
     * 
     * @return the image of the pizza selected.
     */
    protected Image getPizzaImage() {
        if (pizza instanceof Deluxe) {
            return DeluxeImage.getImage();
        }
        if (pizza instanceof Hawaiian) {
            return HawaiianImage.getImage();
        }
        if (pizza instanceof Pepperoni) {
            return PepperoniImage.getImage();
        }
        return null;
    }

    /**
     * Share the current order data to other controllers.
     * 
     * @return the current order.
     */
    protected Order getOrder() {
        return order;
    }

    /**
     * Share the placed order list to other controllers.
     * 
     * @return the placed order list.
     */
    protected ArrayList<Order> getPlaceOrders() {
        return placeOrders;
    }

    /**
     * Place order to the order list.
     * 
     * @param order to be placed.
     * @return true if successfully placed, false if the order already exists in the
     *         list.
     */
    protected boolean placeOrder(Order order) {
        for (Order o : placeOrders) {
            if (o.isSameOrder(order.getOrderNumber())) {
                return false;
            }
        }
        placeOrders.add(order);
        return true;
    }

    /**
     * Check if the phone number input is valid. Only non-empty and integer parsable
     * string is allowed.
     * 
     * @param number string input to be checked.
     * @return true if the number is valid, false otherwise.
     */
    private boolean checkPhoneNumber(String number) {
        try {
            if (number.isEmpty()) {
                throw new NullPointerException("Empty Phone Number!");
            } else {
                try {
                    Integer.parseInt(number);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Invalid Input: " + number);
                    alert.setContentText("Please enter numbers only.");
                    alert.showAndWait();
                    return false;
                }
                customerNumber = number;
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Please enter a phone number.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Check if a new order is needed to be created.
     * 
     * @param number input to be checked.
     * @return true if a different number is entered, false otherwise.
     */
    private boolean checkNewOrder(String number) {
        if (order != null) {
            if (order.isSameOrder(number)) {
                return false;
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Different Phone Number Detected!");
            alert.setContentText("Previous order is discard. Now making order: " + number + ".");
            alert.showAndWait();
        }
        return true;
    }

    /**
     * Create an instance of pizza according to the type of pizza button pressed and
     * start customizing. Open up the window for customization.
     * 
     * @param event expected to be a button pressing event.
     * @throws IOException for IO Exception.
     */
    @FXML
    protected void onPizzaButton(ActionEvent event) throws IOException {
        boolean flag = true;
        flag = checkPhoneNumber(phoneNumber.getText());

        Button button = (Button) event.getSource();
        pizzaName = button.getText();
        pizza = PizzaMaker.createPizza(pizzaName);

        if (flag) {
            if (checkNewOrder(customerNumber)) {
                order = new Order(customerNumber);
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PizzaView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 580, 700);
            PizzaController pizzaView = fxmlLoader.getController();
            pizzaView.setMainController(this);
            stage.setTitle("Pizza Customization");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Open up the window for manipulating the current order.
     * 
     * @throws IOException when the fxml file is not found.
     */
    @FXML
    protected void onCurrentOrder() throws IOException {
        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("No Order Available!");
            alert.setContentText("Type in phone number and select a pizza to create an order.");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CurrentOrderView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 370);
            CurrentOrderController currentOrderView = fxmlLoader.getController();
            currentOrderView.setMainController(this);
            stage.setTitle("Order Detail");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Open up the window for manipulating the order list.
     * 
     * @throws IOException when the fxml file is not found.
     */
    @FXML
    protected void onStoreOrder() throws IOException {
        if (placeOrders.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("No Order Placed!");
            alert.setContentText("Go to Curren Order icon to place an order.");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreOrderView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 370);
            StoreOrderController storeOrderView = fxmlLoader.getController();
            storeOrderView.setMainController(this);
            stage.setTitle("Store Orders");
            stage.setScene(scene);
            stage.show();
        }

    }

}
