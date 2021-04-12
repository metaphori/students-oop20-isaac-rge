package ryleh.view.menu;


import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ryleh.common.Config;
import ryleh.core.GameEngine;

public class RylehPauseMenu {
    private final Stage popupStage;
    private final Scene pauseScene;
    private final Stage primaryStage;
    private final MenuFactory factory;

    public RylehPauseMenu(final Stage primaryStage) {
        factory = new MenuFactory();
        this.primaryStage = primaryStage;
        factory.levelFont = Font.loadFont("assets/fonts/manaspc.ttf", factory.scaledSize);
        final Runnable resume = new Runnable() {
            @Override
            public void run() {
                primaryStage.getScene().getRoot().setEffect(null);
                popupStage.hide();
                GameEngine.resumeEngine();
            } 
        };
        factory.box.getChildren().add(factory.createCustomButton("Resume", "Resume the game", resume));
        factory.box.getChildren().add(factory.createCustomButton("Quit Game", "Exit to desktop", () -> {
            factory.createCustomAlert();
        }));
        factory.separator.setOrientation(Orientation.HORIZONTAL);
        factory.separator.setTranslateX(factory.scaledSize / 2);
        factory.description.setFont(new Font(factory.scaledSize));
        factory.description.setFill(factory.startColor);
        factory.description.setTranslateY(factory.separator.getTranslateY() + 10);
        factory.box.setAlignment(Pos.CENTER_LEFT);
        factory.box.setTranslateX(factory.scaledSize);
        factory.box.getChildren().addAll(factory.separator, factory.description);
        factory.pane.setLeft(factory.box);

        popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode().equals(KeyCode.P)) {
                   resume.run();
            }
        });
        pauseScene = new Scene(factory.pane, Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT);
    }

    /**
     * This method will be called every time the player presses the key "P" to pause the engine inside "GameEngine" class
     * It renders a new pop-up stage that contains the pause menu.
    */
    public void renderPauseMenu() {
        this.primaryStage.getScene().getRoot().setEffect(new GaussianBlur());
        factory.pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);");
        pauseScene.setFill(Color.TRANSPARENT);
        popupStage.setScene(pauseScene);
        popupStage.show();
    }
}
