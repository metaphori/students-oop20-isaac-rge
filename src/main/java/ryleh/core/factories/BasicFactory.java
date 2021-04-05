package ryleh.core.factories;

import ryleh.common.Circle2d;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.core.GameEngine;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.BulletComponent;
import ryleh.model.components.DrunkComponent;
import ryleh.model.components.HealthIntComponent;
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
import ryleh.view.other.RockGraphicComponent;

public class BasicFactory {
     private static BasicFactory instance;
     private Entity player;

     private BasicFactory() { }

     public static BasicFactory getInstance(){
            if (instance == null) {
                    instance = new BasicFactory();
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
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         if (player == null) {
             player = e;
         }
         return e;
     }

     public Entity createRock(final World world, final ViewHandler view) {
         Entity e = GameEngine.entityBuilder()
                 .type(Type.ROCK)
                 .position(600, 400)
                 .view(new RockGraphicComponent())
                 .bbox(new CircleHitBox(45))
                 .build();
         world.addGameObject(e.getGameObject());
         view.addGraphicComponent(e.getView());
         return e;
     }

     public Entity getPlayer() {
         return this.player;
     }
}
