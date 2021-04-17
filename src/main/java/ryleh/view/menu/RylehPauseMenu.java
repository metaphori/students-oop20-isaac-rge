package ryleh.view.menu;


import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ryleh.Ryleh;
import ryleh.common.Config;
import ryleh.core.GameEngine;

public class RylehPauseMenu {
    private final Stage popupStage;
    private final Scene pauseScene;
    private final Stage primaryStage;
    private final MenuFactory factory;
    private final BorderPane pane;
    private final VBox box;

    public RylehPauseMenu(final Stage primaryStage) {
        final Separator separator = new Separator();
        this.pane = new BorderPane();
        this.box = new VBox();
        factory = new MenuFactoryImpl();
        this.primaryStage = primaryStage;
        factory.setLevelFont(Font.loadFont(Ryleh.class.getResource("/assets/fonts/manaspc.ttf")
                .toExternalForm(), factory.getScaledSize()));
        final Runnable resume = new Runnable() {
            @Override
            public void run() {
                primaryStage.getScene().getRoot().setEffect(null);
                popupStage.hide();
                GameEngine.resumeEngine();
            } 
        };

        this.box.getChildren().add(factory.createCustomButton("Resume", "Resume the game", resume));
        Node developButton = factory.createCustomButton("Developer Mode: OFF", "Enable/Disable developer mode", 
                () -> {
                    GameEngine.setDeveloper(!GameEngine.isDeveloper());
                    ((Text) ((HBox) this.box.getChildren().get(1)).getChildren().get(1))
                    .setText("Developer Mode: " + (GameEngine.isDeveloper() ? "ON" : "OFF"));
                });
        this.box.getChildren().add(developButton);
        this.box.getChildren().add(factory.createCustomButton("Quit Game", "Exit to desktop", () -> {
            factory.createCustomAlert("Do you really want to quit?");
        }));
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setTranslateX(factory.getScaledSize() / 2);
        factory.getDescription().setFont(new Font(factory.getScaledSize()));
        factory.getDescription().setFill(factory.getStartColor());
        factory.getDescription().setTranslateY(separator.getTranslateY() + 10);
        this.box.setAlignment(Pos.CENTER_LEFT);
        this.box.setTranslateX(factory.getScaledSize());
        this.box.getChildren().addAll(separator, factory.getDescription());
        this.pane.setLeft(this.box);

        popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode().equals(KeyCode.P)) {
                   resume.run();
            }
        });
        pauseScene = new Scene(this.pane, Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT);
    }

    /**
     * This method will be called every time the player presses the key "P" to pause the engine inside "GameEngine" class
     * It renders a new pop-up stage that contains the pause menu.
    */
    public void renderPauseMenu() {
        this.primaryStage.getScene().getRoot().setEffect(new GaussianBlur());
        this.pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);");
        pauseScene.setFill(Color.TRANSPARENT);
        popupStage.setScene(pauseScene);
        popupStage.show();
    }
}
