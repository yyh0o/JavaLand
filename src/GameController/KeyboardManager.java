package GameController;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyboardManager {
    private Scene listener;
    private ArrayList<String> input;

    public KeyboardManager(Scene initListener, ArrayList initInput){
        listener = initListener;
        input = initInput;
        listener.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();

                        if (!input.contains(code))
                            input.add(code);
                    }
                }
        );
        listener.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        input.remove(code);
                    }
                }
        );
    }

    public ArrayList<String> getInput() {
        return input;
    }
}
