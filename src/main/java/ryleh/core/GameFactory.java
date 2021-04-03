package ryleh.core;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.LurkerComponent;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.components.ShooterComponent;
import ryleh.model.components.SpinnerComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.view.ViewHandler;

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
                 .position(0, 0)
                 .with(new PhysicsComponent(world, 1000))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         if(player==null) {
        	 player = e;
         }
         return e;
     }
     public Entity createEnemyShooter(final World world,final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SHOOTER)
                 .position(300, 800)
                 .with(new ShooterComponent(world,view))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemySpinner(final World world,final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_SPINNER)
                 .position(700, 200)
                 .with(new SpinnerComponent(world,view))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyDrunk(final World world,final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_DRUNK)
                 .position(500, 500)
                 .with(new DrunkComponent(world))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createBullet(final World world,final ViewHandler view,final P2d origin,final V2d direction) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_BULLET)
                 .position(0, 0)
                 .with(new BulletComponent(world,origin,direction))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
     public Entity createEnemyLurker(final World world,final ViewHandler view) {
    	 Entity e = GameEngine.entityBuilder()
                 .type(Type.ENEMY_LURKER)
                 .position(1000, 100)
                 .with(new LurkerComponent(world,player))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
}
