package ryleh.view;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import ryleh.Ryleh;
import ryleh.common.Config;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;

/**
 * A class to Completely handle the view.
 */
public class ViewHandler {
	/**
	 * Duration of fading transition for item effect text.
	 */
    private static final double FT_DURATION = 4000;
    private Stage stage;
    private List<GraphicComponent> graphicComponents;
    private Scene scene;
    private Parent root;
    private Rectangle rectangle;
    private Text lives;
    private Text level;
    private Text item;
    private Font font;

    /**
     * Creates a new Instance of ViewHandler with the given stage.
     * @param stage the stage that needs to be set.
     */
    public ViewHandler(final Stage stage) {
        this.stage = stage;
        this.rectangle = new Rectangle(Textures.BACKGROUND.getWidth(), Textures.BACKGROUND.getHeight());
        this.rectangle.setFill(Textures.BACKGROUND.getImagePattern());
        this.font = Font.loadFont(Ryleh.class.getResource("/assets/fonts/manaspc.ttf")
                		.toExternalForm(), 37 * Config.SCALE_MODIFIER);
        this.setLives();
        this.setLevel();
        this.setItemPickUp();
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(rectangle); 
        ((AnchorPane) root).getChildren().add(this.lives);
        ((AnchorPane) root).getChildren().add(this.level);
        ((AnchorPane) root).getChildren().add(this.item);
        scene = new Scene(root);
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(final WindowEvent event) {
                    Platform.exit();
                }
        });
        this.stage.setScene(scene);
        this.stage.setFullScreen(false);
        //this.stage.setFullScreen(true);
        this.graphicComponents = new ArrayList<>();
    }
    /**
     * A method to set the text that represents the current level.
     */
    private void setLevel() {
    	this.level = new Text("Level: 1");
        this.level.setFont(this.font);
        this.level.setX((Config.STANDARD_WIDTH / 16) * 2 + (75 * Config.SCALE_MODIFIER));
        this.level.setY((Config.STANDARD_HEIGHT / 9) * 1);
        this.level.setFill(Color.WHITE);
	}
    /**
     * A method to set the text that represents the current item effect.
     */
    private void setItemPickUp() {
    	this.item = new Text("Item effect:");
        this.item.setFont(this.font);
        this.item.setX((Config.STANDARD_WIDTH / 16) * 2);
        this.item.setY((Config.STANDARD_HEIGHT / 9) * 8 + (20 * Config.SCALE_MODIFIER));
        this.item.setFill(Color.WHITE);
        this.item.setVisible(false);
	}
    /**
     * A method to set the text that represents the remaining lives.
     */
	private void setLives() {
    	this.lives = new Text("Lives: 3");
        this.lives.setFont(this.font);
        this.lives.setX((Config.STANDARD_WIDTH / 16) * 11 + (50 * Config.SCALE_MODIFIER));
        this.lives.setY((Config.STANDARD_HEIGHT / 9) * 1);
        this.lives.setFill(Color.WHITE);
    }

    /**
     * A method to remove a GraphicComponent from the list of GraphicComponents and removes it from the scene.
     * @param graphic The GraphicComponents that needs to be removed from the list of GraphicComponents and from the scene.
     */
    public void removeGraphicComponent(final GraphicComponent graphic) {
    	graphic.onRemoved(e -> {
    		FilteredList<Node> list = ((AnchorPane) scene.getRoot()).getChildren().filtered(i -> (graphic).getNode().equals(i));
    		if (!list.isEmpty()) {
    			list.get(0).setVisible(false);
    		}
    		this.graphicComponents.remove(graphic);
    	});
    }

    /**
     * A method that returns the number of remaining lives.
     * @return the number of remaining lives.
     */
    public Text getLives() {
		return this.lives;
	}
    /**
     * A method that returns the last item effect.
     * @return the number of remaining lives.
     */
    public Text getItemPickUp() {
		return this.item;
	}
    /**
     * A method that returns the current level number.
     * @return the current level number.
     */
	public Text getLevel() {
		return this.level;
	}
	
	/**
	 * A method to set scene of the current level.
	 */
	public void setLevelScene() {
      root = new AnchorPane();
      root.setStyle("-fx-background-color: black;");
      ((AnchorPane) root).getChildren().add(rectangle);
      ((AnchorPane) root).getChildren().add(this.lives);
      ((AnchorPane) root).getChildren().add(this.level);
      ((AnchorPane) root).getChildren().add(this.item);
      scene.setRoot(root);
 	}

	/**
	 * A method that adds a GraphicCimponent to the list of GraphicComponents and adds it on the scene.
	 * @param graphic The GraphicComponent to be added to the list of GraphicComponents and to the scene.
	 */
	public void addGraphicComponent(final GraphicComponent graphic) {
    	this.graphicComponents.add(graphic);
    	graphic.onAdded(scene);
	}

	/**
	 * A method that returns the list of GraphicComponents.
	 * @return The list of GraphicComponents.
	 */
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
	 * A method that returns the current scene.
	 * @return The current scene.
	 */
	public Scene getScene() {
		return scene;
	}
	/**
	 * A method that sets visibility of item to true and plays a fading transition for the item effects text.
	 */
	public void playFt() {
		this.item.setVisible(true);
		final FadeTransition ft = new FadeTransition(Duration.millis(FT_DURATION), this.item);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
	}
}
