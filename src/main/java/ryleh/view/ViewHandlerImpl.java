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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import ryleh.Ryleh;

/**
 * A class to Completely handle the view.
 */
public class ViewHandlerImpl implements ViewHandler {
	/**
	 * Duration of fading transition for item effect text.
	 */
    private static final double FT_DURATION = 4000;
    /**
    *The width of the screen. 
    */
    public static int STANDARD_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
	/**
	 * the height of the screen.
	 */
    public static int STANDARD_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    /**
     * The modifier to set the correct proportion of the view.
     */
    public static double SCALE_MODIFIER = (double) (ViewHandlerImpl.STANDARD_WIDTH / 1920.0);
    public static int getSTANDARD_WIDTH() {
        return STANDARD_WIDTH;
    }
    public static void setSTANDARD_WIDTH(int sTANDARD_WIDTH) {
        STANDARD_WIDTH = sTANDARD_WIDTH;
    }
    public static int getSTANDARD_HEIGHT() {
        return STANDARD_HEIGHT;
    }
    public static void setSTANDARD_HEIGHT(int sTANDARD_HEIGHT) {
        STANDARD_HEIGHT = sTANDARD_HEIGHT;
    }
    public static double getSCALE_MODIFIER() {
        return SCALE_MODIFIER;
    }
    public static void setSCALE_MODIFIER(double sCALE_MODIFIER) {
        SCALE_MODIFIER = sCALE_MODIFIER;
    }
    private Stage stage;
    private List<GraphicComponent> graphicComponents;
    private Scene scene;
    private Parent root;
    private final Rectangle rectangle;
    private Text lives;
    private Text level;
    private Text item;
    private Text tutorialCommands;
    private Text tutorialObjective;
    private final Font font;
    private boolean isFirstRoom;

    /**
     * Creates a new Instance of ViewHandler with the given stage.
     * @param stage the stage that needs to be set.
     */
    public ViewHandlerImpl(final Stage stage) {
        this.stage = stage;
        this.rectangle = new Rectangle(Textures.BACKGROUND.getWidth(), Textures.BACKGROUND.getHeight());
        this.rectangle.setFill(Textures.BACKGROUND.getImagePattern());
        this.font = Font.loadFont(Ryleh.class.getResource("/assets/fonts/manaspc.ttf")
                		.toExternalForm(), 37 * SCALE_MODIFIER);
        this.setLives();
        this.setLevel();
        this.setTutorial();
        this.setItemPickUp();
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(rectangle); 
        ((AnchorPane) root).getChildren().add(this.lives);
        ((AnchorPane) root).getChildren().add(this.level);
        ((AnchorPane) root).getChildren().add(this.item);
        ((AnchorPane) root).getChildren().add(this.tutorialCommands);
        ((AnchorPane) root).getChildren().add(this.tutorialObjective);
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
        this.isFirstRoom = true;
    }
    /**
     * A method to set the text that represents the current level.
     */
    private void setLevel() {
    	this.level = new Text();
        this.level.setFont(this.font);
        this.level.setX((STANDARD_WIDTH / 16) * 2 + (75 * SCALE_MODIFIER));
        this.level.setY((STANDARD_HEIGHT / 9) * 1);
        this.level.setFill(Color.WHITE);
	}
    /**
     * A method to set the tutorial text that will appear on the first level.
     */
    private void setTutorial() {
    	this.tutorialCommands = new Text("List of commands:\n W -> move forward \n A -> move left \n S -> move downward \n D -> move right \n Spacebar -> shoot");
        this.tutorialCommands.setFont(this.font);
        this.tutorialCommands.setX((STANDARD_WIDTH / 16) * 3 + (75 * SCALE_MODIFIER));
        this.tutorialCommands.setY((STANDARD_HEIGHT / 9) * 3);
        this.tutorialCommands.setFill(Color.WHITE);
        this.tutorialObjective = new Text("In order to win you must \nbeat level 30.\nAfter defeating all \nenemies on a level a \ndoor will open and bring \nyou to the next level.\n"
        		+ "Every 3 levels an \nitem will spawn.\n"
        		+ "If you lose all your \nlives you will lose.\nGood luck and have fun!");
        this.tutorialObjective.setFont(this.font);
        this.tutorialObjective.setX((STANDARD_WIDTH / 16) * 8 + (75 * SCALE_MODIFIER));
        this.tutorialObjective.setY((STANDARD_HEIGHT / 9) * 2);
        this.tutorialObjective.setFill(Color.WHITE);
    }
    /**
     * A method to set the text that represents the current item effect.
     */
    private void setItemPickUp() {
    	this.item = new Text();
        this.item.setFont(this.font);
        this.item.setX((STANDARD_WIDTH / 16) * 2);
        this.item.setY((STANDARD_HEIGHT / 9) * 8 + (20 * SCALE_MODIFIER));
        this.item.setFill(Color.WHITE);
        this.item.setVisible(false);
	}
    /**
     * A method to set the text that represents the remaining lives.
     */
	private void setLives() {
    	this.lives = new Text("Lives: 3");
        this.lives.setFont(this.font);
        this.lives.setX((STANDARD_WIDTH / 16) * 11 + (50 * SCALE_MODIFIER));
        this.lives.setY((STANDARD_HEIGHT / 9) * 1);
        this.lives.setFill(Color.WHITE);
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
	 * {@inheritDoc}
	 */
	@Override
	public void displayLevelScene() {
      root = new AnchorPane();
      root.setStyle("-fx-background-color: black;");
      ((AnchorPane) root).getChildren().add(rectangle);
      ((AnchorPane) root).getChildren().add(this.lives);
      ((AnchorPane) root).getChildren().add(this.level);
      ((AnchorPane) root).getChildren().add(this.item);
      if (this.isFirstRoom) {
    	  ((AnchorPane) root).getChildren().add(this.tutorialCommands);
    	  ((AnchorPane) root).getChildren().add(this.tutorialObjective);
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
