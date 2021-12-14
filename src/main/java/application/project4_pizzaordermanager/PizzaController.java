package application.project4_pizzaordermanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller for PizzaView that handles pizza customization for all three kinds
 * of pizza. Handle toppings addings and removings, changing size, showing
 * price, and adding the pizza to the order.
 *
 * @author Yuchen Wei, Denghao Sun
 */

public class PizzaController {
    private static final int MAXTOPPINGS = 7;
    private MainController mainController;
    private Pizza pizza;
    private Order order;
    private String pizzaNamePassed;
    @FXML
    private ComboBox<Size> sizeComboBox;
    @FXML
    private ListView<Topping> addListView;
    @FXML
    private ListView<Topping> selectedListView;
    @FXML
    private ImageView PizzaImage;
    @FXML
    private Text pizzaName;
    @FXML
    private TextField customerNumber;
    @FXML
    private TextField price;

    /**
     * Initialize the ComboBox for choosing Sizes. Fill with all values of the Size
     * Enum and set the default value to Small.
     */
    private void initializeComboBox() {
        try {
            ArrayList<Size> sizeArrayList = new ArrayList<Size>();
            sizeArrayList.add(Size.Small);
            sizeArrayList.add(Size.Medium);
            sizeArrayList.add(Size.Large);
            ObservableList<Size> sizeObservableList = FXCollections.observableArrayList(sizeArrayList);
            sizeComboBox.setItems(sizeObservableList);
            sizeComboBox.setValue(pizza.size);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Something Went Wrong!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Initialize ListViews for toppings to be added and toppings already selected.
     */
    private void initializeListView() {
        try {
            ObservableList<Topping> selectedToppingObservableList = FXCollections.observableArrayList(pizza.toppings);
            selectedListView.setItems(selectedToppingObservableList);
            selectedListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ArrayList<Topping> addArrayList = new ArrayList<Topping>();
            for (Topping e : Topping.values()) {
                if (!pizza.toppings.contains(e)) {
                    addArrayList.add(e);
                }
            }
            ObservableList<Topping> addToppingObservableList = FXCollections.observableArrayList(addArrayList);
            addListView.setItems(addToppingObservableList);
            addListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Something Went Wrong!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Update the pizza with two listviews of toppings selected.
     */
    private void updatePizza() {
        ObservableList<Topping> selectedToppingObservableList = (selectedListView.getItems());
        pizza.toppings.clear();
        pizza.toppings.addAll(selectedToppingObservableList);
        onSizeChange();
    }

    /**
     * Get data from the main controller and initialize the View.
     *
     * @param controller passed from the MainController Class.
     */
    public void setMainController(MainController controller) {
        mainController = controller;
        pizzaNamePassed = mainController.getPizzaName();
        pizza = mainController.getPizza();
        // order = mainController.findOrder(mainController.getPhoneNumber());
        order = mainController.getOrder();

        if (pizza instanceof Deluxe)
            pizzaName.setText("Deluxe");
        if (pizza instanceof Hawaiian)
            pizzaName.setText("Hawaiian");
        if (pizza instanceof Pepperoni)
            pizzaName.setText("Pepperoni");

        customerNumber.setText(mainController.getPhoneNumber());
        PizzaImage.setImage(mainController.getPizzaImage());

        initializeComboBox();
        initializeListView();

        price.setText(String.format("%.2f", pizza.price()));
    }

    /**
     * Update the size and price when a new size is selected.
     */
    @FXML
    protected void onSizeChange() {
        pizza.size = sizeComboBox.getSelectionModel().getSelectedItem();
        price.setText(String.format("%.2f", pizza.price()));
    }

    /**
     * Add toppings selected in the add list to the pizza. Toppings selected would
     * then be moved from the add list to the selected list.
     */
    @FXML
    protected void onAddTopping() {
        ObservableList<Topping> addingList = addListView.getSelectionModel().getSelectedItems();
        if (addingList.size() + selectedListView.getItems().size() <= MAXTOPPINGS) {
            selectedListView.getItems().addAll(addingList);
            addListView.getItems().removeAll(addingList);
            updatePizza();
            price.setText(String.format("%.2f", pizza.price()));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Max Number of Toppings!");
            alert.setContentText("You can't have more than 7 toppings.");
            alert.showAndWait();
        }

    }

    /**
     * Remove toppings selected in the selected list from the pizza. Toppings
     * selected would then be moved from the selected list to the add list.
     */
    @FXML
    protected void onRemoveTopping() {
        ObservableList<Topping> removingList = selectedListView.getSelectionModel().getSelectedItems();
        if (selectedListView.getItems().size() - removingList.size() >= pizza.getMinTopping()) {
            selectedListView.getItems().removeAll(removingList);
            addListView.getItems().addAll(removingList);
            updatePizza();
            price.setText(String.format("%.2f", pizza.price()));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Minimum Number of Toppings!");
            alert.setContentText("You can't have less than " + pizza.getMinTopping() + " toppings.");
            alert.showAndWait();
        }

    }

    /**
     * Add the pizza to the order, create a new same pizza.
     */
    @FXML
    protected void onAddPizza() {
        order.addPizza(pizza);

        pizza = PizzaMaker.createPizza(pizzaNamePassed);
        updatePizza();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Pizza Added Successfully!");
        alert.setContentText("Pizza info: " + pizza.toString());
        alert.showAndWait();

    }

}
