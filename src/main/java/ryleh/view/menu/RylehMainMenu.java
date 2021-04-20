package ryleh.view.menu;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import ryleh.Ryleh;
import ryleh.controller.core.GameEngine;
import ryleh.view.ViewHandlerImpl;

/**
 * This class represents the game's main menu. It uses MenuFactory to
 * instantiate graphic parts inside the constructor. To render it, call "show"
 * method.
 */
public class RylehMainMenu {
    /**
     * Standard scaling of the menu.
     */
    private static final double STANDARD_SCALE = 1920.0;
    private final Stage primaryStage;
    private MenuFactory factory;
    private static final List<Pair<Integer, Integer>> STANDARD_SCALES = List.of(new Pair<>(1920, 1080),
            new Pair<>(1280, 720), new Pair<>(800, 450));
    /**
     * 
     * @param primaryStage The stage that contains the Menu
     */
    public RylehMainMenu(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Shows Ryleh's Call main menu.
     */
    public void show() {
        this.factory = new MenuFactoryImpl();
        this.newMenu();
        this.primaryStage.show();
    }

    /**
     * Creates a new Main Menu with custom buttons and a description for every of
     * them.
     *
     */
    public void newMenu() {
        final Image background = new Image("/assets/texture/menu/menu.png");
        final BackgroundImage menu = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
               new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        final Background back = new Background(menu);
        final Separator separator = new Separator();
        final BorderPane pane = new BorderPane();
        final VBox leftBox = new VBox();
        factory.setLevelFont(Font.loadFont(Ryleh.class.getResource("/assets/fonts/saturno.ttf").toExternalForm(),
                factory.getScaledSize()));
        leftBox.getChildren().add(factory.createCustomButton("Start Game", "Start a new adventure", () -> {
            final GameEngine engine = new GameEngine();
            engine.initGame(primaryStage);
            engine.mainLoop();
        }));
        leftBox.getChildren().add(factory.createCustomButton("Quit Game", "Exit to desktop", () -> {
            factory.createCustomAlert("Do you really want to quit?");
        }));
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setTranslateX(factory.getScaledSize() / 2);
        factory.getDescription().setFont(new Font(factory.getScaledSize()));
        factory.getDescription().setFill(factory.getStartColor());
        factory.getDescription().setTranslateY(separator.getTranslateY() + factory.getScaledSize() / 2);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setTranslateX(factory.getScaledSize());
        leftBox.getChildren().addAll(separator, factory.getDescription());
        pane.setBackground(back);
        pane.setLeft(leftBox);
        pane.setRight(createComboBox());
        this.primaryStage
                .setScene(new Scene(pane, ViewHandlerImpl.getStandardWidth(), ViewHandlerImpl.getStandardHeight()));
        this.primaryStage.setResizable(true);
        this.primaryStage.setTitle("Ryleh's Call");
        this.primaryStage.setOnCloseRequest(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            });
        });
    }
    private Node createComboBox() {
        final List<Pair<Integer, Integer>> resolutions = new ArrayList<>();
        resolutions.addAll(STANDARD_SCALES);
        final ComboBox<Pair<Integer, Integer>> combo = new ComboBox<>();
        combo.setPromptText("Select a Resolution");
        combo.getItems().addAll(resolutions);
        combo.setOnAction(e -> {
            ViewHandlerImpl.setStandardWidth(combo.getValue().getKey());
            ViewHandlerImpl.setStandardHeight(combo.getValue().getValue());
            ViewHandlerImpl.setScaleModifier(ViewHandlerImpl.getStandardWidth() / STANDARD_SCALE);
            this.show();
        });
        return combo;
    }
}
