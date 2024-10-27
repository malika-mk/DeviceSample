module org.example.devicesample {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.devicesample to javafx.fxml;
    exports org.example.devicesample;
}