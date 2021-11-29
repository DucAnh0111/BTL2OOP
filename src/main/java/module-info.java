module com.example.btlso2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens BomberGame to javafx.fxml;
    exports BomberGame;
}