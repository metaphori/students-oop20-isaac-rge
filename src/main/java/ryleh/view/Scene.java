package ryleh.view;

import java.util.List;

import javafx.stage.Stage;

public class Scene {

    private Stage stage;
    private List<View> graphicComponents;

    public Scene(final Stage stage) {
        this.stage = stage;
    }

    public void render(String id) {
        // TODO Auto-generated method stub
        this.graphicComponents.forEach(i -> i.render());
    }

}
