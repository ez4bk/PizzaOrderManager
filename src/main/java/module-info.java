module application.project4_pizzaordermanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens application.project4_pizzaordermanager to javafx.fxml;
    exports application.project4_pizzaordermanager;
}