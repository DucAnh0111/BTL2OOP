module com.example.btlso2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens BomberGame to javafx.fxml;
    exports BomberGame;
}