package ryleh.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.stage.Stage;
import ryleh.common.P2d;
import ryleh.controller.Entity;
import ryleh.controller.InputController;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.view.ViewHandler;

public class GameState {
    private ViewHandler view;
    private World world;
    private List<Entity> objects;
    private Map<String, String> gameVars;
    private boolean isGameOver = false;

    public GameState(final Stage mainStage) {
        try {
                view = new ViewHandler(mainStage);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }
        world = new World();
        objects = new ArrayList<>();
        gameVars = new HashMap<>();
        gameVars.put("Version", "0.1");
        objects.add(GameFactory.getInstance().createPlayer(world, view));
        objects.add(GameFactory.getInstance().createEnemyDrunk(world, view));
       // objects.add(GameFactory.getInstance().createEnemyShooter(world, view));
        //objects.add(GameFactory.getInstance().createEnemySpinner(world, view));
        objects.add(GameFactory.getInstance().createEnemyLurker(world, view));
        InputController.initInput(view.getScene(), this.getEntityByType(Type.PLAYER).get());
    }

    public void updateState(int deltaTime) {
        for (final Entity object : this.objects) {
            object.getGameObject().onUpdate(deltaTime);
            //TODO change next "render" method to accept in input a object P2d
            object.getView().render(toPoint2D(object.getGameObject().getPosition()));
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

	public ViewHandler getScene() {
		return view;
	}
	
	private Point2D toPoint2D (final P2d point) {
		return new Point2D(point.x, point.y);
	}
	
	public Optional<Entity> getEntityByType(final Type type) {
		return objects.stream().filter(i -> i.getGameObject().getType().equals(type)).findAny();
	}
}
