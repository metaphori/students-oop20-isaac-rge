package ryleh.view;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ryleh.common.Config;
import ryleh.view.enemies.EnemyDrunkGraphicComponent;

public class ViewHandler {

    private Stage stage;
    private List<GraphicComponent> graphicComponents;
    private Scene scene;
    private Parent root;
    private Rectangle rectangle;

    public ViewHandler(final Stage stage) {
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

    public void setLevelScene() {
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(rectangle);
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
