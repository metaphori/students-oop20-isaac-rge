package ryleh.view.menu;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ryleh.Ryleh;
import ryleh.common.Config;
import ryleh.core.GameEngine;

public class RylehMainMenu extends MenuFactoryImpl {
	
	/**
	 * Creates a new Main Menu with custom buttons and a description for every of them.
	 * @param primaryStage The stage that contains the Menu
	 */
    public RylehMainMenu(final Stage primaryStage) {
        final MenuFacotry factory = new MenuFactoryImpl();
        factory.setLevelFont(Font.loadFont(Ryleh.class.getResource("/assets/fonts/saturno.ttf")
                .toExternalForm(), factory.getScaledSize()));
        factory.getBox().getChildren().add(factory.createCustomButton("Start Game", "Start a new adventure", () -> {
        final GameEngine engine = new GameEngine();
            engine.initGame(primaryStage);
            engine.mainLoop();
        }));
        factory.getBox().getChildren().add(factory.createCustomButton("Quit Game", "Exit to desktop", () -> {
            factory.createCustomAlert("Do you really want to quit?");
        }));
        factory.getSeparator().setOrientation(Orientation.HORIZONTAL);
        factory.getSeparator().setTranslateX(factory.getScaledSize() / 2);
        factory.getDescription().setFont(new Font(factory.getScaledSize()));
        factory.getDescription().setFill(factory.getStartColor());
        factory.getDescription().setTranslateY(factory.getSeparator().getTranslateY() + factory.getScaledSize() / 2);
        factory.getBox().setAlignment(Pos.CENTER_LEFT);
        factory.getBox().setTranslateX(factory.getScaledSize());
        factory.getBox().getChildren().addAll(factory.getSeparator(), factory.getDescription());
        factory.getPane().setLeft(factory.getBox());
        primaryStage.setScene(new Scene(factory.getPane(), Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ryleh's Call");
        primaryStage.show();
    }
}
