package ryleh.core.factories;

import ryleh.common.Circle2d;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.core.GameEngine;
import ryleh.core.GameState;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.CollisionComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.FireComponent;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.components.ItemComponent;
import ryleh.model.components.LurkerComponent;
import ryleh.model.components.PhysicsComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.components.ShooterComponent;
import ryleh.model.components.SpinnerComponent;
import ryleh.view.ViewHandler;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;
import ryleh.view.enemies.EnemyLurkerGraphicComponent;
import ryleh.view.enemies.EnemyShooterGraphicComponent;
import ryleh.view.enemies.EnemySpinnerGraphicComponent;
import ryleh.view.other.BulletGraphicComponent;
import ryleh.view.other.FireGraphicComponent;
import ryleh.view.other.ItemGraphicComponent;
import ryleh.view.other.RockGraphicComponent;

public class BasicFactory {
     private static BasicFactory instance;

     private BasicFactory() { }

     public static BasicFactory getInstance(){
            if (instance == null) {
                    instance = new BasicFactory();
            }
            return instance;
    }

     public Entity createPlayer(final GameState state, final P2d position) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.PLAYER)
                 .position(position)
                 .with(new PhysicsComponent(state.getWorld(), 1000))
                 .with(new HealthIntComponent(state.getWorld(), 3))
                 .view(new PlayerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(85)))
                 .zIndex(1)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         ((PlayerGraphicComponent) e.getView()).setDirection(((PhysicsComponent) e.getGameObject().getComponent(PhysicsComponent.class).get()).getDirection());
         return e;
     }
     public Entity createBullet(final GameState state, final P2d origin, final V2d direction, final Type type) {
    	 Type bulletType = type.equals(Type.PLAYER) ? Type.PLAYER_BULLET : Type.ENEMY_BULLET;
         Entity e = GameEngine.entityBuilder()
                 .type(bulletType)
                 .position(origin)
                 .with(new BulletComponent(state.getWorld(), origin, direction))
                 .view(new BulletGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(5)))
                 .zIndex(0)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
     public Entity createRock(final GameState state, final P2d position) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.ROCK)
                 .position(position)
                 .view(new RockGraphicComponent())
                 .bbox(new CircleHitBox(45))
                 .zIndex(1)
                 .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
     public Entity createItem(final GameState state, final P2d position) {
         Entity e = GameEngine.entityBuilder()
                         .type(Type.ITEM)
                         .position(position)
                         .with(new ItemComponent(state.getWorld()))
                         .view(new ItemGraphicComponent())
                         .bbox(new CircleHitBox(new Circle2d(30)))
                         .zIndex(0)
                         .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }

     public Entity createFire(final GameState state, final P2d position) {
         Entity e = GameEngine.entityBuilder()
                         .type(Type.FIRE)
                         .position(position)
                         .with(new FireComponent(state.getWorld()))
                         .with(new CollisionComponent(state.getWorld()))
                         .view(new FireGraphicComponent())
                         .bbox(new CircleHitBox(new Circle2d(45)))
                         .zIndex(1)
                         .build();
         state.getWorld().addGameObject(e.getGameObject());
         state.getView().addGraphicComponent(e.getView());
         return e;
     }
}
