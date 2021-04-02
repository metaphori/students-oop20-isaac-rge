package ryleh.core;

import ryleh.controller.Entity;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;
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
                 .position(0, 0)
                 .with(new PhysicsComponent(world, 1000))
                 .view(new PlayerGraphicComponent())
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }
}
