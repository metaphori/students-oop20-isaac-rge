package ryleh.core;

import java.util.List;

import ryleh.common.Circle2d;
import ryleh.common.P2d;
import ryleh.common.Shape2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.components.ItemComponent;
import ryleh.model.components.LurkerComponent;
import ryleh.model.components.PhysicsComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.physics.HitBox;
import ryleh.model.components.ShooterComponent;
import ryleh.model.components.SpinnerComponent;
import ryleh.view.ViewHandler;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;
import ryleh.view.enemies.EnemyDrunkSpinnerGraphicComponent;
import ryleh.view.enemies.EnemyLurkerGraphicComponent;
import ryleh.view.enemies.EnemyShooterGraphicComponent;
import ryleh.view.enemies.EnemySpinnerGraphicComponent;
import ryleh.view.other.ItemGraphicComponent;
import ryleh.view.other.RockGraphicComponent;

public class GameFactory {
     private static GameFactory instance;
     private Entity player = null;

     private GameFactory() { }

     public static GameFactory getInstance(){
            if (instance == null) {
                    instance = new GameFactory();
            }
            return instance;
    }

     public Entity createPlayer(final World world, final ViewHandler view) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.PLAYER)
                 .position(960, 540)
                 .with(new PhysicsComponent(world, 1000))
                 .with(new HealthIntComponent(world, 3))
                 .view(new PlayerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(100)))
                 .zIndex(2)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         if (player == null) {
        	player = e;
         }
         ((PlayerGraphicComponent) e.getView()).setDirection( ((PhysicsComponent) e.getGameObject().getComponent(PhysicsComponent.class).get()).getDirection());
         return e;
     }
     public Entity createEnemyShooter(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SHOOTER)
                 .position(300, 800)
                 .with(new ShooterComponent(world, view, player))
                 .view(new EnemyShooterGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemySpinner(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SPINNER)
                 .position(900, 500)
                 .with(new SpinnerComponent(world, view))
                 .view(new EnemySpinnerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .zIndex(1)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunk(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNK)
                 .position(960, 540)
                 .with(new DrunkComponent(world))
                 .view(new EnemyDrunkGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .zIndex(3)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createBullet(final World world, final ViewHandler view, final P2d origin, final V2d direction) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_BULLET)
                 .position(0, 0)
                 .with(new BulletComponent(world, origin, direction))
                 .view(new EnemyShooterGraphicComponent())
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyLurker(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_LURKER)
                 .position(800, 200)
                 .with(new LurkerComponent(world, player))
                 .view(new EnemyLurkerGraphicComponent(player.getGameObject()))
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunkSpinner(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNKSPINNER)
                 .position(1200, 540)
                 .with(new DrunkComponent(world))
                 .view(new EnemyDrunkSpinnerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(50)))
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }

     public Entity createRock(final World world, final ViewHandler view) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.ROCK)
                 .position(600, 400)
                 .view(new RockGraphicComponent())
                 .bbox(new CircleHitBox(45))
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createItem(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
    			 .type(Type.ITEM)
    			 .position(500, 600)
    			 .with(new ItemComponent(world))
    			 .view(new ItemGraphicComponent())
    			 .bbox(new CircleHitBox(new Circle2d(30)))
    			 .zIndex(0)
    			 .build();
    	 world.addGameObject(e.getGameObject());
    	 view.addGraphicComponent(e.getView());
    	 return e;
     }
}
