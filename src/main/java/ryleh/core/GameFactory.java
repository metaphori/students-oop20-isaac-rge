package ryleh.core;

import ryleh.controller.GameObjectController;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;

public class GameFactory {
     private static GameFactory instance;

     private GameFactory() { }

     public static GameFactory getInstance(){
            if (instance == null) {
                    instance = new GameFactory();
            }
            return instance;
    }

     public GameObjectController createPlayer(final World world) {
         GameObjectController e = GameEngine.entityBuilder()
                 .type(Type.PLAYER)
                 .position(0, 0)
                 .with(new PhysicsComponent(world))
                 .build();
         world.addGameObject(e.getGameObject());
         return e;
     }
}
