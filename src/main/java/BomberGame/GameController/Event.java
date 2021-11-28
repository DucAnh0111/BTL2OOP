package BomberGame.GameController;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Event {
    public static ArrayList<KeyCode> controllerKeyList = new ArrayList<KeyCode>();
    public static void attachEventHandlers(Scene scene){
        keyReleaseHandler releasedKey = new keyReleaseHandler();
        keyPressedHandler pressedKey = new keyPressedHandler();
        scene.setOnKeyReleased(releasedKey);
        scene.setOnKeyPressed(pressedKey);
    }
    public static List getInputList(){
        return controllerKeyList;
    }
}

class keyReleaseHandler implements javafx.event.EventHandler<KeyEvent>{
    public keyReleaseHandler() {
    }
    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if ( Event.controllerKeyList.contains(code) )
            Event.controllerKeyList.remove(code);
    }
}

class keyPressedHandler implements javafx.event.EventHandler<KeyEvent>{
    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (!Event.controllerKeyList.contains(code))
            Event.controllerKeyList.add(code);
    }
}
