package ryleh.core;

import ryleh.common.Circle2d;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.LurkerComponent;
import ryleh.model.components.PhysicsComponent;
import ryleh.view.AnimatedPlayerGraphicComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.components.ShooterComponent;
import ryleh.model.components.SpinnerComponent;
import ryleh.view.ViewHandler;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;
import ryleh.view.enemies.EnemyLurkerGraphicComponent;
import ryleh.view.enemies.EnemyShooterGraphicComponent;
import ryleh.view.enemies.EnemySpinnerGraphicComponent;

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
                 .position(950, 540)
                 .with(new PhysicsComponent(world, 1000))
                 .view(new AnimatedPlayerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(190)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         if (player == null) {
        	 player = e;
         }
         ((AnimatedPlayerGraphicComponent) e.getView()).setDirection( ((PhysicsComponent) e.getGameObject().getComponent(PhysicsComponent.class).get()).getDirection());
         return e;
     }
     public Entity createEnemyShooter(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SHOOTER)
                 .position(300, 800)
                 .with(new ShooterComponent(world, view))
                 .view(new EnemyShooterGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemySpinner(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SPINNER)
                 .position(700, 200)
                 .with(new SpinnerComponent(world, view))
                 .view(new EnemySpinnerGraphicComponent())
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
                 .view(new AnimatedPlayerGraphicComponent() )
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyLurker(final World world, final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_LURKER)
                 .position(1000, 100)
                 .with(new LurkerComponent(world, player))
                 .view(new EnemyLurkerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
}
