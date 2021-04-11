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
import ryleh.controller.InputController;
import ryleh.controller.events.EventHandler;
import ryleh.controller.levels.LevelHandler;
import ryleh.core.factories.BasicFactory;
import ryleh.core.factories.EnemyFactory;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;
import ryleh.view.ViewHandler;

public class GameState {
    private ViewHandler view;
    private World world;
    private List<Entity> entities;
    private Map<String, String> gameVars;
    private boolean isGameOver = false;
    private EventHandler eventHandler;
    private InputController input;
    private LevelHandler levelHandler;
    private Entity player;

    public GameState(final Stage mainStage) {
        try {
                view = new ViewHandler(mainStage);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }
        this.eventHandler = new EventHandler(this);
        world = new World(eventHandler);
        entities = new ArrayList<>();
        gameVars = new HashMap<>();
        gameVars.put("Version", "0.1");

        this.levelHandler = new LevelHandler(this);
        this.player = GameFactory.getInstance().createPlayer(world, view, levelHandler.getPosition(levelHandler.playerSpawn));
        //NEXT LINES SHOULD ALL BE DELEGATED TO LEVEL HANDLER
       // entities.add(player);
//        //objects.add(GameFactory.getInstance().createEnemyDrunk(world, view));
//        entities.add(GameFactory.getInstance().createEnemyShooter(world, view));
//        entities.add(GameFactory.getInstance().createEnemyDrunk(world, view));
//        //objects.add(GameFactory.getInstance().createEnemyShooter(world, view));
//        entities.add(GameFactory.getInstance().createEnemySpinner(world, view));
//        entities.add(GameFactory.getInstance().createEnemyDrunkSpinner(world, view));
//        //objects.add(GameFactory.getInstance().createBullet(world, view ,objects.get(2).getGameObject().getPosition(),new V2d(1,0)));
//        entities.add(GameFactory.getInstance().createEnemyLurker(world, view));
//        entities.add(GameFactory.getInstance().createItem(world, view));
//        entities.add(GameFactory.getInstance().createRock(world, view));
//        entities.add(GameFactory.getInstance().createFire(world, view));

        //objects.add(GameFactory.getInstance().createBullet(world, view, new P2d(200, 200), new V2d(1,0)));
        //objects.add(GameFactory.getInstance().createItem(world, view))
        generateNewLevel();
    }
    public Entity getPlayer() {
		return this.player;
	}
	public void addEntity(Entity entity) {
    	this.entities.add(entity);
    }
	public void generateNewLevel() {
		world.getGameObjects().clear();
		entities.clear();
		view.getGraphicComponents().clear();
		try {
			view.setLevelScene();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		levelHandler.generateNewLevel();
		view.addGraphicComponent(player.getView());
		world.addGameObject(player.getGameObject());
		entities.add(player);
		((PhysicsComponent)player.getGameObject().getComponent(PhysicsComponent.class).get()).setPosition(levelHandler.getPosition(levelHandler.playerSpawn));
		entities.addAll(levelHandler.getEntities());
		Collections.sort(entities, new Comparator<Entity>() {
			@Override
			public int compare(final Entity o1, final Entity o2) {
				return o1.getGameObject().getzIndex() - o2.getGameObject().getzIndex();
			}
	    });
		input = new InputController(this);
	    input.initInput();
	    levelHandler.debug();

	}

    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    	view.removeGraphicComponent(entity.getView());
    	world.removeGameObject(entity.getGameObject());
    }

    public void updateState(double dt) {
        input.updateInput();
        for (final Entity object : this.entities) {
            object.getGameObject().onUpdate(dt);
            object.getView().render(toPoint2D(new P2d(
                    object.getGameObject().getPosition().x, object.getGameObject().getPosition().y)), dt);
        }
        eventHandler.checkEvents();
    }

    public void updateRender(double deltaF) {
    	 //TODO change next "render" method to accept in input a object P2d
    	for(final Entity object : this.entities) {
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
	return entities.stream().filter(i -> i.getGameObject().getType().equals(type)).findAny();
    }

    public World getWorld() {
        return this.world;
    }

    public void updateInput() {
	this.input.updateInput();
    }
    public List<Entity> getEntities() {
	return this.entities;
    }
}
