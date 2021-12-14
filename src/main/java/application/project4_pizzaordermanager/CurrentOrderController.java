package application.project4_pizzaordermanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

/**
 * Controller for CurrentOrderView that handles order-detail display, editing,
 * and confirmation.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class CurrentOrderController {

    private Order currentOrder;
    private double subTotalNumber = 0;
    private double taxTotalNumber = 0;
    private double orderTotalNumber = 0;
    private MainController mainController;
    @FXML
    public TextField customerNumber;
    @FXML
    public TextField subTotal;
    @FXML
    public TextField salesTax;
    @FXML
    public TextField orderTotal;
    @FXML
    public ListView<Pizza> pizzaList;

    /**
     * Initialize the ListView that displays pizzas in the order.
     */
    private void initializePizzaList() {
        try {
            ObservableList<Pizza> pizzaListInTheOrder = FXCollections.observableArrayList(currentOrder.getPizzas());
            pizzaList.setItems(pizzaListInTheOrder);
            pizzaList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Something Went Wrong!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Update and set the prices for sub-total, sales tax, and order total to their
     * corresponding text field.
     */
    private void setPrices() {
        subTotalNumber = currentOrder.getSubtotal();
        subTotal.setText(String.format("%.2f", subTotalNumber));

        taxTotalNumber = currentOrder.getTax();
        salesTax.setText(String.format("%.2f", taxTotalNumber));

        orderTotalNumber = currentOrder.getOrderTotal();
        orderTotal.setText(String.format("%.2f", orderTotalNumber));
    }

    /**
     * Get data from the main controller and initialize the View.
     * 
     * @param controller passed from the MainController Class
     */
    public void setMainController(MainController controller) {
        mainController = controller;

        currentOrder = mainController.getOrder();
        customerNumber.setText(currentOrder.getOrderNumber());

        initializePizzaList();
        setPrices();

    }

    /**
     * Remove a pizza from the order.
     */
    @FXML
    protected void onRemovePizza() {
        ObservableList<Pizza> removingPizzas = pizzaList.getSelectionModel().getSelectedItems();
        if (removingPizzas != null) {
            pizzaList.getItems().removeAll(removingPizzas);
            for (Pizza p : removingPizzas) {
                currentOrder.removePizza(p);
            }
            setPrices();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Success!");
            alert.setHeaderText("Pizza Removed Successfully!");
            alert.setContentText(removingPizzas.size() + " pizza(s) is(are) removed.");
            alert.showAndWait();
        }

    }

    /**
     * PLace the order to the order list.
     */
    @FXML
    protected void onPlaceOrder() {
        if (mainController.placeOrder(currentOrder)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Success!");
            alert.setHeaderText("Order Placed Successfully!");
            alert.setContentText("Customer phone number: " + mainController.getPhoneNumber());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed!");
            alert.setHeaderText("Order Placed Failed!");
            alert.setContentText("Order: " + mainController.getPhoneNumber() + " already exists!");
            alert.showAndWait();
        }
    }
}
