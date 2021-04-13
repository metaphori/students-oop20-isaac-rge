package ryleh.view;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
import ryleh.Ryleh;
import ryleh.common.Config;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;

public class ViewHandler {

    private Stage stage;
    private List<GraphicComponent> graphicComponents;
    private Scene scene;
    private Parent root;
    private Rectangle rectangle;
    private Text lives;
    private Text level;
    private Font font;

    public ViewHandler(final Stage stage) {
        this.stage = stage;
        this.rectangle = new Rectangle(Textures.BACKGROUND.getWidth(), Textures.BACKGROUND.getHeight());
        this.rectangle.setFill(Textures.BACKGROUND.getImagePattern());
        this.font = Font.loadFont(Ryleh.class.getResource("/assets/fonts/manaspc.ttf")
                .toExternalForm(), 37 * Config.SCALE_MODIFIER);
        this.lives = new Text("Lives: 3");
        this.lives.setFont(this.font);
        this.lives.setX((Config.STANDARD_WIDTH / 16) * 11 + (50 * Config.SCALE_MODIFIER));
        this.lives.setY((Config.STANDARD_HEIGHT / 9) * 1);
        this.lives.setFill(Color.WHITE);
        this.level = new Text("Level: 1");
        this.level.setFont(this.font);
        this.level.setX((Config.STANDARD_WIDTH / 16) * 2 + (75 * Config.SCALE_MODIFIER));
        this.level.setY((Config.STANDARD_HEIGHT / 9) * 1);
        this.level.setFill(Color.WHITE);
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(rectangle); 
        ((AnchorPane) root).getChildren().add(this.lives);
        ((AnchorPane) root).getChildren().add(this.level);
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


    public void removeGraphicComponent(final GraphicComponent graphic) {
    	graphic.onRemoved(e -> {
    		((AnchorPane) scene.getRoot()).getChildren().filtered(i ->
            (graphic).getNode().equals(i)).get(0).setVisible(false);
    		this.graphicComponents.remove(graphic);
    	});

    	/*if (FadeProv.class.isInstance(graphic)){
        	FadeTransition trans = ((FadeProv) graphic).getTransition();
        	trans.setOnFinished(e -> {
        		((AnchorPane) scene.getRoot()).getChildren().filtered(i ->
                (graphic).getNode().equals(i)).get(0).setVisible(false);
        		this.graphicComponents.remove(graphic);
        	});
        	trans.play();
        } else {
        	((AnchorPane) scene.getRoot()).getChildren().filtered(i ->
            (graphic).getNode().equals(i)).get(0).setVisible(false);
        	this.graphicComponents.remove(graphic);
        }*/
    }
  public Text getLives() {
		return this.lives;
	}

	public Text getLevel() {
		return this.level;
	}
  public void setLevelScene() {
      root = new AnchorPane();
      root.setStyle("-fx-background-color: black;");
      ((AnchorPane) root).getChildren().add(rectangle);
      ((AnchorPane) root).getChildren().add(this.lives);
      ((AnchorPane) root).getChildren().add(this.level);
      scene.setRoot(root);
  }

  public void addGraphicComponent(final GraphicComponent graphic) {
    	this.graphicComponents.add(graphic);
    	graphic.onAdded(scene);
  }

  public List<GraphicComponent> getGraphicComponents() {
		return graphicComponents;
	}

	public Stage getStage() {
		return stage;
	}

	public Scene getScene() {
		return scene;
	}

}
