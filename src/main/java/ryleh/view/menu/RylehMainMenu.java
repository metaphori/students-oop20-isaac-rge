package ryleh.view.menu;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ryleh.Ryleh;
import ryleh.common.Config;
import ryleh.core.GameEngine;
/**
 * This class represents the game's main menu. It uses MenuFactory to instantiate graphic parts inside the constructor.
 * To render it, call "show" method. 
 */
public class RylehMainMenu {

    private final Stage primaryStage;
    private final MenuFactory factory;

    /**
     * Creates a new Main Menu with custom buttons and a description for every of them.
     * @param primaryStage The stage that contains the Menu
     */
    public RylehMainMenu(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        final Separator separator = new Separator();
        final BorderPane pane = new BorderPane();
        final VBox box = new VBox();
        this.factory = new MenuFactoryImpl();
        factory.setLevelFont(Font.loadFont(Ryleh.class.getResource("/assets/fonts/saturno.ttf")
                .toExternalForm(), factory.getScaledSize()));
        box.getChildren().add(factory.createCustomButton("Start Game", "Start a new adventure", () -> {
        final GameEngine engine = new GameEngine();
            engine.initGame(primaryStage);
            engine.mainLoop();
        }));
        box.getChildren().add(factory.createCustomButton("Quit Game", "Exit to desktop", () -> {
            factory.createCustomAlert("Do you really want to quit?");
        }));
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setTranslateX(factory.getScaledSize() / 2);
        factory.getDescription().setFont(new Font(factory.getScaledSize()));
        factory.getDescription().setFill(factory.getStartColor());
        factory.getDescription().setTranslateY(separator.getTranslateY() + factory.getScaledSize() / 2);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setTranslateX(factory.getScaledSize());
        box.getChildren().addAll(separator, factory.getDescription());
        pane.setLeft(box);
        this.primaryStage.setScene(new Scene(pane, Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT));
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Ryleh's Call");
    }
    /**
     * Shows Ryleh's Call main menu.
     */
    public void show() {
        this.primaryStage.show();
    }
}
