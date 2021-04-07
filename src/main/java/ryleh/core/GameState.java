package ryleh.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private InputController input;

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
        objects.add(GameFactory.getInstance().createEnemySpinner(world, view));
        objects.add(GameFactory.getInstance().createPlayer(world, view));
        objects.add(GameFactory.getInstance().createEnemyDrunk(world, view));
        objects.add(GameFactory.getInstance().createRock(world, view));
        //objects.add(GameFactory.getInstance().createEnemyShooter(world, view));
        //objects.add(GameFactory.getInstance().createEnemyDrunkSpinner(world, view));
        //objects.add(GameFactory.getInstance().createEnemyLurker(world, view));
        //objects.add(GameFactory.getInstance().createBullet(world, view, new P2d(200, 200), new V2d(1,0)));
        //objects.add(GameFactory.getInstance().createItem(world, view));

        Collections.sort(objects, new Comparator<Entity>() {
			@Override
			public int compare(Entity o1, Entity o2) {
				return o1.getGameObject().getzIndex() - o2.getGameObject().getzIndex();
			}
        });

        for( Entity e : objects) {
        	System.out.print(" | " + e.getGameObject().getType() + " " + e.getGameObject().getzIndex());
        }

        input = new InputController(this.view.getScene(), this.getEntityByType(Type.PLAYER).get());
        input.initInput();


        this.eventHandler = new EventHandler(this);
    }

    public void removeEntity(Entity entity) {
    	objects.remove(entity);
    	view.removeGraphicComponent(entity.getView());
    	world.removeGameObject(entity.getGameObject());
    }

    public void updateState(int deltaTime) {
        for (final Entity object : this.objects) {
            object.getGameObject().onUpdate(deltaTime);
            //TODO change next "render" method to accept in input a object P2d
            object.getView().render(toPoint2D(new P2d(object.getGameObject().getPosition().x -95, object.getGameObject().getPosition().y - 95 )), deltaTime);
        }
        eventHandler.checkEvents();
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
	public void updateInput() {
		this.input.updateInput();
	}
}
