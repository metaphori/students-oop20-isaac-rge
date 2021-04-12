package ryleh.core;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameEngine {
    private GameState rylehState;
    private double period = 1000/60;
    private static Timeline loop;
    private static boolean isPaused = false;

    /*
     * 
     */
    public void initGame(final Stage stage) {
        rylehState = new GameState(stage);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode().equals(KeyCode.P)) {
                if (GameEngine.isPaused()) {
                    GameEngine.resumeEngine();
                    isPaused = false;
                } else {
                    GameEngine.pauseEngine();
                    isPaused = true;
                }
            }
        });
    }

    /**
     * Uses a JavaFx class, called Timeline, to handle one keyframe (that corresponds to one gameloop's update). 
     * This operation is done once every "period"
     */
    public void mainLoop() {
        final Duration oneFrameAmt = Duration.millis(period);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
           new EventHandler<>() {
            @Override
            public void handle(final ActionEvent event) {
                if (rylehState.isGameOver()) {
                    renderGameOver();
                }
                rylehState.updateState(period);
            }
        }); // oneFrame

        // sets the game world's game loop (Timeline)
        loop = new Timeline(oneFrame);
        loop.setCycleCount(Animation.INDEFINITE);
        loop.play(); 
    }

    public static EntityBuilder entityBuilder() {
        return new EntityBuilder();
    }
    public static void pauseEngine() {
        loop.pause();
    }
    public static void resumeEngine() {
        loop.playFromStart();
    }
    public static boolean isPaused() {
        return isPaused;
    }
    private void renderGameOver() {
        // TODO Auto-generated method stub 
    }

}
