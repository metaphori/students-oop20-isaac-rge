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
import ryleh.model.components.CollisionComponent;
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
import ryleh.view.other.BulletGraphicComponent;
import ryleh.view.other.FireGraphicComponent;
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

     public Entity createPlayer(final World world, final ViewHandler view, final P2d position) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.PLAYER)
                 .position(position)
                 .with(new PhysicsComponent(world, 1000))
                 .with(new HealthIntComponent(world, 3))
                 .view(new PlayerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(100)))
                 .zIndex(1)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         if (player == null) {
        	player = e;
         }
         ((PlayerGraphicComponent) e.getView()).setDirection( ((PhysicsComponent) e.getGameObject().getComponent(PhysicsComponent.class).get()).getDirection());
         return e;
     }
     public Entity createEnemyShooter(final World world, final ViewHandler view, final P2d position) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SHOOTER)
                 .position(position)
                 .with(new ShooterComponent(world, player))
                 .with(new HealthIntComponent(world,1))
                 .with(new CollisionComponent(world))
                 .view(new EnemyShooterGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .zIndex(1)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemySpinner(final World world, final ViewHandler view, final P2d position) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SPINNER)
                 .position(position)
                 .with(new SpinnerComponent(world))
                 .with(new HealthIntComponent(world,1))
                 .with(new CollisionComponent(world))
                 .view(new EnemySpinnerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .zIndex(10)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunk(final World world, final ViewHandler view, final P2d position) {
    	 Type type = Type.ENEMY_DRUNK;
    	 Entity e = GameEngine.entityBuilder()
                 .type(type)
                 .position(position)
                 .with(new DrunkComponent(world))
                 .with(new HealthIntComponent(world,1))
                 .with(new CollisionComponent(world))
                 .view(new EnemyDrunkGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .with(new HealthIntComponent(world, 1))
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createBullet(final World world, final ViewHandler view, final P2d origin, final V2d direction, final Type type) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(type)
                 .position(origin)
                 .with(new BulletComponent(world, origin, direction))
                 .view(new BulletGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(15)))
                 .zIndex(0)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyLurker(final World world, final ViewHandler view, final P2d position) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_LURKER)
                 .position(position)
                 .with(new LurkerComponent(world, player))
                 .with(new HealthIntComponent(world,1))
                 .with(new CollisionComponent(world))
                 .view(new EnemyLurkerGraphicComponent(player.getGameObject()))
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .zIndex(9)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunkSpinner(final World world, final ViewHandler view, final P2d position) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNKSPINNER)
                 .position(position)
                 .with(new DrunkComponent(world))
                 .with(new HealthIntComponent(world,1))
                 .with(new CollisionComponent(world))
                 .view(new EnemyDrunkSpinnerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(75)))
                 .zIndex(8)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }

     public Entity createRock(final World world, final ViewHandler view, final P2d position) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.ROCK)
                 .position(position)
                 .view(new RockGraphicComponent())
                 .bbox(new CircleHitBox(45))
                 .zIndex(1)
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createItem(final World world, final ViewHandler view, final P2d position) {
    	 Entity e = GameEngine.entityBuilder()
    			 .type(Type.ITEM)
    			 .position(position)
    			 .with(new ItemComponent(world))
    			 .view(new ItemGraphicComponent())
    			 .bbox(new CircleHitBox(new Circle2d(30)))
    			 .zIndex(0)
    			 .build();
    	 world.addGameObject(e.getGameObject());
    	 view.addGraphicComponent(e.getView());
    	 return e;
     }

     public Entity createFire(final World world, final ViewHandler view, final P2d position) {
    	 Entity e = GameEngine.entityBuilder()
    			 .type(Type.FIRE)
    			 .position(position)
    			 .with(new CollisionComponent(world))
    			 .view(new FireGraphicComponent())
    			 .bbox(new CircleHitBox(45))
    			 .zIndex(1)
    			 .build();
    	 world.addGameObject(e.getGameObject());
    	 view.addGraphicComponent(e.getView());
    	 return e;
     }
}
