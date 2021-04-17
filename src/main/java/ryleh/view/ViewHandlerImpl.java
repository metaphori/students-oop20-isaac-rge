package ryleh.view;

import java.util.ArrayList;
import java.util.List;
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
/**
 * A class to Completely handle the view.
 */
public class ViewHandlerImpl implements ViewHandler {
    /**
    *The width of the screen. 
    */
    public static final int STANDARD_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
	/**
	 * the height of the screen.
	 */
    public static final int STANDARD_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    /**
     * The modifier to set the correct proportion of the view.
     */
    public static final double SCALE_MODIFIER = (double) (STANDARD_WIDTH / 1920.0);
    private final Stage stage;
    private final List<GraphicComponent> graphicComponents;
    private final Scene scene;
    private Parent root;
    private final Rectangle rectangle;
    private final GameUI gameUi;
	private boolean isFirstRoom;

    /**
     * Creates a new Instance of ViewHandler with the given stage.
     * @param stage the stage that needs to be set.
     */
    public ViewHandlerImpl(final Stage stage) {
    	this.gameUi = new GameUI();
        this.stage = stage;
        this.rectangle = new Rectangle(Textures.BACKGROUND.getWidth(), Textures.BACKGROUND.getHeight());
        this.rectangle.setFill(Textures.BACKGROUND.getImagePattern());
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(rectangle); 
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
    }
	/**
     * {@inheritDoc}
     */
    @Override
    public void removeGraphicComponent(final GraphicComponent graphic) {
    	graphic.onRemoved(e -> {
    		final FilteredList<Node> list = ((AnchorPane) scene.getRoot()).getChildren().filtered(i -> graphic.getNode().equals(i));
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
      ((AnchorPane) root).getChildren().add(rectangle);
      ((AnchorPane) root).getChildren().add(gameUi.getLevel());
      ((AnchorPane) root).getChildren().add(gameUi.getLives());
      ((AnchorPane) root).getChildren().add(gameUi.getItemPickUp());
      if (this.isFirstRoom) {
    	  for (final Text elem :  gameUi.getTutorial()) {
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
	 * @return a GameUI object.
	 */
	public GameUI getGameUi() {
		return gameUi;
	}
}
