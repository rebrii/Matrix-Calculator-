module com.example.matrix_calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.matrix_calculator to javafx.fxml;
    exports com.example.matrix_calculator;
}