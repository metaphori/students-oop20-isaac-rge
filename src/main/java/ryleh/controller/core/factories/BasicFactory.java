package ryleh.controller.core.factories;

import ryleh.common.Circle2d;
import ryleh.common.GameMath;
import ryleh.common.Point2d;
import ryleh.common.Vector2d;
import ryleh.controller.Entity;
import ryleh.controller.EntityImpl;
import ryleh.controller.core.GameEngine;
import ryleh.controller.core.GameState;
import ryleh.model.Type;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.CollisionComponent;
import ryleh.model.components.DoorComponent;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.components.PlayerComponent;
import ryleh.model.components.ShootingComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.view.graphics.PlayerGraphicComponent;
import ryleh.view.graphics.other.BulletGraphicComponent;
import ryleh.view.graphics.other.DoorGraphicComponent;
import ryleh.view.graphics.other.FireGraphicComponent;
import ryleh.view.graphics.other.ItemGraphicComponent;
import ryleh.view.graphics.other.RockGraphicComponent;

public final class BasicFactory {
    private static BasicFactory instance;

    private BasicFactory() {
    }

    public static BasicFactory getInstance() {
        if (instance == null) {
            instance = new BasicFactory();
        }
        return instance;
    }

    public Entity createPlayer(final GameState state, final Point2d position) {
        EntityImpl e = GameEngine.entityBuilder().type(Type.PLAYER).position(position)
                .with(new PlayerComponent(state.getWorld(), 1000)).with(new HealthIntComponent(state.getWorld(), 3))
                .with(new ShootingComponent(state.getWorld(), 1.0))
                .view(new PlayerGraphicComponent(GameMath.toPoint2D(position))).bbox(new CircleHitBox(new Circle2d(70)))
                .zIndex(1).build();
        state.getWorld().addGameObject(e.getGameObject());
        state.getView().addGraphicComponent(e.getView());
        ((PlayerGraphicComponent) e.getView()).setDirection(
                ((PlayerComponent) e.getGameObject().getComponent(PlayerComponent.class).get()).getDirection());
        return e;
    }

    public Entity createBullet(final GameState state, final Point2d origin, final Vector2d direction, final Type type) {
        final Type bulletType = type.equals(Type.PLAYER) ? Type.PLAYER_BULLET : Type.ENEMY_BULLET;
        Entity e = GameEngine.entityBuilder().type(bulletType).position(origin)
                .with(new BulletComponent(state.getWorld(), origin, direction))
                .view(new BulletGraphicComponent(GameMath.toPoint2D(origin), bulletType))
                .bbox(new CircleHitBox(new Circle2d(5))).zIndex(0).build();
        state.getWorld().addGameObject(e.getGameObject());
        state.getView().addGraphicComponent(e.getView());
        return e;
    }

    public EntityImpl createRock(final GameState state, final Point2d position) {
        EntityImpl e = GameEngine.entityBuilder().type(Type.ROCK).position(position)
                .view(new RockGraphicComponent(GameMath.toPoint2D(position))).bbox(new CircleHitBox(75)).zIndex(1)
                .build();
        state.getWorld().addGameObject(e.getGameObject());
        state.getView().addGraphicComponent(e.getView());
        return e;
    }

    public EntityImpl createItem(final GameState state, final Point2d position) {
        EntityImpl e = GameEngine.entityBuilder().type(Type.ITEM).position(position)
                .with(new CollisionComponent(state.getWorld(), Type.ITEM))
                .view(new ItemGraphicComponent(GameMath.toPoint2D(position))).bbox(new CircleHitBox(new Circle2d(30)))
                .zIndex(0).build();
        state.getWorld().addGameObject(e.getGameObject());
        state.getView().addGraphicComponent(e.getView());
        return e;
    }

    public EntityImpl createFire(final GameState state, final Point2d position) {
        EntityImpl e = GameEngine.entityBuilder().type(Type.FIRE).position(position)
                .with(new CollisionComponent(state.getWorld(), Type.FIRE))
                .view(new FireGraphicComponent(GameMath.toPoint2D(position))).bbox(new CircleHitBox(new Circle2d(45)))
                .zIndex(1).build();
        state.getWorld().addGameObject(e.getGameObject());
        state.getView().addGraphicComponent(e.getView());
        return e;
    }

    public EntityImpl createDoor(final GameState state, final Point2d position) {
        DoorGraphicComponent door = new DoorGraphicComponent(GameMath.toPoint2D(position));
        EntityImpl e = GameEngine.entityBuilder().type(Type.DOOR).position(position).view(door)
                .with(new DoorComponent(state.getWorld(), door.getTotalAnimDuration()))
                .bbox(new CircleHitBox(new Circle2d(75))).zIndex(0).build();
        state.getWorld().addGameObject(e.getGameObject());
        state.getView().addGraphicComponent(e.getView());
        return e;
    }
}
