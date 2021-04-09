package ryleh.core;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameEngine {
    private GameState rylehState;
    private double period = 1000/60;

    /*
     * 
     */
    public void initGame(final Stage stage) {
        rylehState = new GameState(stage);
    }

    /*
     * Uses a JavaFx class, called Timeline, to handle one keyframe (that corresponds to one gameloop's update). 
     * This operation is done once every "period"
     */
    public void mainLoop() {
        final Duration oneFrameAmt = Duration.millis(period);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
           new EventHandler<>() {

        @Override
        public void handle(final ActionEvent event) {
            rylehState.updateState(period);
        }
        }); // oneFrame

        // sets the game world's game loop (Timeline)
        final Timeline loop = new Timeline(oneFrame);
        loop.setCycleCount(Animation.INDEFINITE);
        loop.play(); 
    }

    public static EntityBuilder entityBuilder() {
        return new EntityBuilder();
    }

}
