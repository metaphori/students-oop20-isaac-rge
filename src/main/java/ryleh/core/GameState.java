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
import ryleh.common.Config;
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
    private final ViewHandler view;
    private final World world;
    private final List<Entity> entities;
    private final Map<String, String> gameVars;
    private boolean isGameOver = false;
    private final EventHandler eventHandler;
    private final InputController input;
    private final LevelHandler levelHandler;
    private final Entity player;

    public GameState(final Stage mainStage) {
        view = new ViewHandler(mainStage);
        this.eventHandler = new EventHandler(this);
        world = new World(eventHandler);
        entities = new ArrayList<>();
        gameVars = new HashMap<>();
        gameVars.put("Version", "0.1");
        this.levelHandler = new LevelHandler(this);
        this.player = BasicFactory.getInstance().createPlayer(this, levelHandler.getPosition(levelHandler.playerSpawn));
        input = new InputController(this);
        this.generateNewLevel();
    }
    public Entity getPlayer() {
	return this.player;
    }
    public void addEntity(final Entity entity) {
    	this.entities.add(entity);
    }
    public void generateNewLevel() {
	world.getGameObjects().clear();
	entities.clear();
	view.getGraphicComponents().clear();
	view.setLevelScene();
	levelHandler.generateNewLevel();
	view.addGraphicComponent(player.getView());
	world.addGameObject(player.getGameObject());
	entities.add(player);
	//player.getGameObject().setPosition(levelHandler.getPosition(levelHandler.playerSpawn));
	((PhysicsComponent) player.getGameObject().getComponent(PhysicsComponent.class).get())
	    .setPosition(levelHandler.getPosition(levelHandler.playerSpawn));

	entities.addAll(levelHandler.getEntities());
	Collections.sort(entities, new Comparator<Entity>() {
	    @Override
		public int compare(final Entity o1, final Entity o2) {
			return o1.getGameObject().getzIndex() - o2.getGameObject().getzIndex();
		}
	});
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

    public boolean isGameOver() {
        return isGameOver;
    }

    public ViewHandler getView() {
	return view;
    }
	
    private Point2D toPoint2D (final P2d point) {
        return new Point2D(point.x * Config.SCALE_MODIFIER, point.y * Config.SCALE_MODIFIER);
    }
	
    public LevelHandler getLevelHandler() {
        return this.levelHandler;
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
