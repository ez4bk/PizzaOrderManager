package application.project4_pizzaordermanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

/**
 * Controller for StoreOrderView that handles order cancellation and exporting.
 *
 * @author Yuchen Wei, Denghao Sun
 */
public class StoreOrderController {

    private ArrayList<Order> placeOrders;
    private MainController mainController;
    @FXML
    public ComboBox<String> numberComboBox;
    @FXML
    public TextField orderTotalText;
    @FXML
    public ListView<Pizza> storeOrdersView;

    /**
     * The method to find if the given order is already in the order list. Compare
     * the given customer number with the one saved.
     * 
     * @param number of the customer.
     * @return the order in the order list if it is found; otherwise, null if it is
     *         not found.
     */
    private Order findOrderInList(String number) {
        for (Order o : placeOrders) {
            if (o.isSameOrder(number)) {
                return o;
            }
        }
        return null;
    }

    /**
     * Update and set the price for the order total to their corresponding text
     * filed.
     */
    private void setPrice() {
        String numberSelected = numberComboBox.getSelectionModel().getSelectedItem();
        Order order = findOrderInList(numberSelected);
        Double price = order.getOrderTotal();
        orderTotalText.setText(String.format("%.2f", price));
    }

    /**
     * Initialize the ComboBox for choosing customer's phone number in the order
     * list.
     */
    private void initializeComboBox() {
        try {
            ArrayList<String> numberArrayList = new ArrayList<String>();
            for (Order o : placeOrders) {
                numberArrayList.add(o.getOrderNumber());
            }
            ObservableList<String> numberObservableList = FXCollections.observableArrayList(numberArrayList);
            numberComboBox.setItems(numberObservableList);
            numberComboBox.setValue(numberObservableList.get(0));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Something Went Wrong!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Initialize the ListView for pizzas in the selected order.
     */
    private void initializeListView() {
        try {
            String numberSelected = numberComboBox.getSelectionModel().getSelectedItem();
            ObservableList<Pizza> orderPizzaObservableList = FXCollections
                    .observableArrayList(findOrderInList(numberSelected).getPizzas());
            storeOrdersView.setItems(orderPizzaObservableList);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Something Went Wrong!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Get data from the main controller and initialize the View.
     * 
     * @param controller passed from the MainController Class
     */
    public void setMainController(MainController controller) {
        mainController = controller;
        placeOrders = mainController.getPlaceOrders();

        initializeComboBox();
        initializeListView();
        setPrice();

    }

    /**
     * Initialize the list view and call the setPrice() method to set the price to
     * the text filed.
     */
    @FXML
    protected void onNumberChange() {
        initializeListView();
        setPrice();
    }

    /**
     * Cancel the order selected.
     */
    @FXML
    protected void onCancelOrder() {
        String numberSelected = numberComboBox.getSelectionModel().getSelectedItem();
        if (numberComboBox.getItems().size() == 1) {
            placeOrders.remove(findOrderInList(numberSelected));
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Success!");
            alert.setHeaderText("Order Removed Successfully!");
            alert.setContentText("0 orders is available. Closing...");
            alert.showAndWait();
            Stage stage = (Stage) numberComboBox.getScene().getWindow();
            stage.close();
        } else {
            placeOrders.remove(findOrderInList(numberSelected));
            numberComboBox.setValue(numberComboBox.getItems().get(0));
            onNumberChange();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Success!");
            alert.setHeaderText("Order Removed Successfully!");
            alert.setContentText(placeOrders.size() + " orders left.");
            alert.showAndWait();
        }
    }

    /**
     * Export the order information to a text file.
     * 
     * @param file selected by the user.
     * @throws IOException when the file is not found.
     */
    private void exportOrder(File file) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        String numberSelected = numberComboBox.getSelectionModel().getSelectedItem();
        Order order = findOrderInList(numberSelected);
        writer.println("Customer Phone Number: " + order.getOrderNumber() + "\tTotal Price: " + order.getOrderTotal());
        for (Pizza p : order.getPizzas()) {
            writer.println(p.toString());
        }
        writer.println("");
        writer.close();
    }

    /**
     * Export the selected order information to a text file chosen by the user.
     * 
     * @throws IOException when the file is not found.
     */
    @FXML
    protected void onExportOrder() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage stage = new Stage();
        File targetFile = chooser.showOpenDialog(stage);
        exportOrder(targetFile);
    }
}
