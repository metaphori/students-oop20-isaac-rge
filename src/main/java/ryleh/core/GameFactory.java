package ryleh.core;

import ryleh.common.Circle2d;
import ryleh.controller.Entity;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.view.PlayerGraphicComponent;
import ryleh.view.ViewHandler;

public class GameFactory {
     private static GameFactory instance;

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
                 .view(new PlayerGraphicComponent())
                 .bbox(new CircleHitBox(new Circle2d(190)))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
}
