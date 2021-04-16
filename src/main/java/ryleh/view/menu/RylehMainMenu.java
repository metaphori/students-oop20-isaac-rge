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

public class RylehMainMenu extends MenuFactoryImpl {

    private VBox box;
    private Separator separator;
    private BorderPane pane;
    private Stage primaryStage;
    private final MenuFactory factory;
	
	/**
	 * Creates a new Main Menu with custom buttons and a description for every of them.
	 * @param primaryStage The stage that contains the Menu
	 */
    public RylehMainMenu(final Stage primaryStage) {
    	this.primaryStage = primaryStage;
        this.separator = new Separator();
        this.pane = new BorderPane();
        this.box = new VBox();
        this.factory = new MenuFactoryImpl();
        factory.setLevelFont(Font.loadFont(Ryleh.class.getResource("/assets/fonts/saturno.ttf")
                .toExternalForm(), factory.getScaledSize()));
        this.box.getChildren().add(factory.createCustomButton("Start Game", "Start a new adventure", () -> {
        final GameEngine engine = new GameEngine();
            engine.initGame(primaryStage);
            engine.mainLoop();
        }));
        this.box.getChildren().add(factory.createCustomButton("Quit Game", "Exit to desktop", () -> {
            factory.createCustomAlert("Do you really want to quit?");
        }));
        this.separator.setOrientation(Orientation.HORIZONTAL);
        this.separator.setTranslateX(factory.getScaledSize() / 2);
        factory.getDescription().setFont(new Font(factory.getScaledSize()));
        factory.getDescription().setFill(factory.getStartColor());
        factory.getDescription().setTranslateY(this.separator.getTranslateY() + factory.getScaledSize() / 2);
        this.box.setAlignment(Pos.CENTER_LEFT);
        this.box.setTranslateX(factory.getScaledSize());
        this.box.getChildren().addAll(this.separator, factory.getDescription());
        this.pane.setLeft(this.box);
        this.primaryStage.setScene(new Scene(this.pane, Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT));
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Ryleh's Call");
        this.primaryStage.show();
    }
}
