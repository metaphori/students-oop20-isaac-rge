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
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.controller.EventHandler;
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
    private EventHandler eventHandler;

    public GameState(final Stage mainStage) {
        try {
                view = new ViewHandler(mainStage);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }
        world = new World(eventHandler);
        objects = new ArrayList<>();
        gameVars = new HashMap<>();
        gameVars.put("Version", "0.1");
        objects.add(GameFactory.getInstance().createPlayer(world, view));
        objects.add(GameFactory.getInstance().createEnemyDrunk(world, view));
        objects.add(GameFactory.getInstance().createEnemyShooter(world, view));
        objects.add(GameFactory.getInstance().createEnemySpinner(world, view));
       // objects.add(GameFactory.getInstance().createEnemyDrunkSpinner(world, view));
        //objects.add(GameFactory.getInstance().createBullet(world, view ,objects.get(2).getGameObject().getPosition(),new V2d(1,0)));
        objects.add(GameFactory.getInstance().createEnemyLurker(world, view));
        objects.add(GameFactory.getInstance().createRock(world, view));
        InputController.initInput(view.getScene(), this.getEntityByType(Type.PLAYER).get());
        this.eventHandler = new EventHandler(this);
    }

    public void removeEntity(Entity entity) {
    	objects.remove(entity);
    	view.removeGraphicComponent(entity.getView());
    	world.removeGameObject(entity.getGameObject());
    }

    public void updateState(double deltaU) {
        for (final Entity object : this.objects) {
            object.getGameObject().onUpdate(deltaU);
        }
        eventHandler.checkEvents();
    }
    
    public void updateRender(double deltaF) {
    	 //TODO change next "render" method to accept in input a object P2d
    	for(final Entity object : this.objects) {
    		object.getView().render(toPoint2D(new P2d(object.getGameObject().getPosition().x -95, object.getGameObject().getPosition().y - 95 )), deltaF);
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
