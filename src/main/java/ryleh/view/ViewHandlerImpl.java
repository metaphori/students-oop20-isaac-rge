package ryleh.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ryleh.view.graphics.GraphicComponent;

/**
 * A class to Completely handle the view.
 */
public class ViewHandlerImpl implements ViewHandler {
    /**
     * To calculate scale of window size.
     */
    private static final double STANDARD_SCALE = 1920.0;
    private final Stage stage;
    private final List<GraphicComponent> graphicComponents;
    private final Scene scene;
    private Parent root;
    private final Rectangle background;
    private final GameUI gameUi;
    private boolean isFirstRoom;

    /**
     * The width of the screen.
     */
    private static int standardWidth = (int) Screen.getPrimary().getBounds().getWidth();
    /**
     * The height of the screen.
     */
    private static int standardHeight = (int) Screen.getPrimary().getBounds().getHeight();
    /**
     * The modifier to set the correct proportion of the view.
     */
    private static double scaleModifier = (double) (ViewHandlerImpl.standardWidth / STANDARD_SCALE);

    public static int getStandardWidth() {
        return standardWidth;
    }

    public static void setStandardWidth(final int standardwidth) {
        standardWidth = standardwidth;
    }

    public static int getStandardHeight() {
        return standardHeight;
    }

    public static void setStandardHeight(final int standardheight) {
        standardHeight = standardheight;
    }

    public static double getScaleModifier() {
        return scaleModifier;
    }

    public static void setScaleModifier(final double scalemodifier) {
        scaleModifier = scalemodifier;
    }

    /**
     * Creates a new Instance of ViewHandler with the given stage.
     * 
     * @param stage the stage that needs to be set.
     */
    public ViewHandlerImpl(final Stage stage) {
        this.gameUi = new GameUI();
        this.stage = stage;
        this.background = new Rectangle(Textures.BACKGROUND.getWidth(), Textures.BACKGROUND.getHeight());
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(background);
        scene = new Scene(root);
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                Platform.exit();
            }
        });
        this.stage.setScene(scene);
        this.stage.setFullScreen(false);
        this.graphicComponents = new ArrayList<>();
        this.isFirstRoom = true;
        this.stage.setOnCloseRequest(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGraphicComponent(final GraphicComponent graphic) {
        graphic.onRemoved(e -> {
            final FilteredList<Node> list = ((AnchorPane) scene.getRoot()).getChildren()
                    .filtered(i -> graphic.getNode().equals(i));
            if (!list.isEmpty()) {
                list.get(0).setVisible(false);
            }
            this.graphicComponents.remove(graphic);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayLevelScene() {
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        final Random generator = new Random();
        switch (generator.nextInt(3)) {
        case 1:
            this.background.setFill(Textures.BACKGROUND2.getImagePattern());
            break;
        case 2:
            this.background.setFill(Textures.BACKGROUND3.getImagePattern());
            break;
        case 0:
        default:
            this.background.setFill(Textures.BACKGROUND.getImagePattern());
            break;
        }
        ((AnchorPane) root).getChildren().add(background);
        ((AnchorPane) root).getChildren().add(gameUi.getLevel());
        ((AnchorPane) root).getChildren().add(gameUi.getLives());
        ((AnchorPane) root).getChildren().add(gameUi.getItemPickUp());
        if (this.isFirstRoom) {
            for (final Text elem : gameUi.getTutorial()) {
                ((AnchorPane) root).getChildren().add(elem);
            }
            this.isFirstRoom = false;
        }
        scene.setRoot(root);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGraphicComponent(final GraphicComponent graphic) {
        this.graphicComponents.add(graphic);
        graphic.onAdded(scene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GraphicComponent> getGraphicComponents() {
        return graphicComponents;
    }

    /**
     * A method that returns the current stage.
     * 
     * @return The current stage.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     * A method that returns the current gameUi.
     * 
     * @return a GameUI object.
     */
    public GameUI getGameUi() {
        return gameUi;
    }
}
