module com.example.btlso2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens BomberGame to javafx.fxml;
    exports BomberGame;
}