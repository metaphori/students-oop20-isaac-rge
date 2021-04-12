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

public class RylehPauseMenu extends RylehMainMenu{
    private final Stage popupStage;
    private final Scene pauseScene;
    private final Stage primaryStage;

    public RylehPauseMenu(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        levelFont = Font.loadFont("assets/fonts/manaspc.ttf", font);
        final Runnable resume = new Runnable() {
            @Override
            public void run() {
                primaryStage.getScene().getRoot().setEffect(null);
                popupStage.hide();
                GameEngine.resumeEngine();
            } 
        };
        this.box.getChildren().add(super.createCustomButton("Resume", "Resume the game", resume));
        this.box.getChildren().add(super.createCustomButton("Quit Game", "Exit to desktop", () -> {
            super.createCustomAlert();
        }));
        super.separator.setOrientation(Orientation.HORIZONTAL);
        super.separator.setTranslateX(font / 2);
        super.description.setFont(new Font(font));
        super.description.setFill(startColor);
        super.description.setTranslateY(separator.getTranslateY() + 10);
        super.box.setAlignment(Pos.CENTER_LEFT);
        super.box.setTranslateX(font);
        super.box.getChildren().addAll(this.separator, this.description);
        super.pane.setLeft(this.box);
        popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode().equals(KeyCode.P)) {
                   resume.run();
            }
        });
        pauseScene = new Scene(pane, Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT);
    }

    /**
     * This method will be called every time the player presses the key "P" to pause the engine inside "GameEngine" class
     * It renders a new pop-up stage that contains the pause menu.
    */
    public void renderPauseMenu() {
        this.primaryStage.getScene().getRoot().setEffect(new GaussianBlur());
        super.pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);");
        pauseScene.setFill(Color.TRANSPARENT);
        popupStage.setScene(pauseScene);
        popupStage.show();
    }
}
