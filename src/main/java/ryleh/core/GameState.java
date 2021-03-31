package ryleh.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.stage.Stage;
import ryleh.controller.GameObjectController;
import ryleh.model.World;
import ryleh.view.Scene;

public class GameState {
    private Scene scene;
    private World world;
    private List<GameObjectController> objects;
    private Map<String, String> gameVars;
    private boolean isGameOver = false;

    public GameState(final Stage mainStage) {
        scene = new Scene(mainStage);
        world = new World();
        objects = new ArrayList<>();
        gameVars = new HashMap<>();
        gameVars.put("Version", "0.1");
    }

    public void updateState() {
        for (final GameObjectController object : this.objects) {
            object.getGameObject().onUpdate();
            object.getView().render(object.getGameObject().getPosition());
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
