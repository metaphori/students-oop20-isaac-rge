package ryleh.view.menu;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ryleh.Ryleh;
import ryleh.common.Config;
import ryleh.core.GameEngine;

public class RylehMainMenu {

    public RylehMainMenu(final Stage primaryStage) {
        final MenuFactory factory = new MenuFactory();
        factory.levelFont = Font.loadFont(Ryleh.class.getResource("/assets/fonts/saturno.ttf")
                .toExternalForm(), factory.scaledSize);
        factory.box.getChildren().add(factory.createCustomButton("Start Game", "Start a new adventure", () -> {
        final GameEngine engine = new GameEngine();
            engine.initGame(primaryStage);
            engine.mainLoop();
        }));
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
        primaryStage.setScene(new Scene(factory.pane, Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ryleh's Call");
        primaryStage.show();
    }
}
