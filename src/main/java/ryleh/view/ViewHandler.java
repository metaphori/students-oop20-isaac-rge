package ryleh.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ryleh.common.Config;
import ryleh.view.enemies.EnemyShooterGraphicComponent;
import ryleh.view.other.ItemGraphicComponent;

public class ViewHandler {

    private Stage stage;
    private List<GraphicComponent> graphicComponents;
    private Scene scene;
    private Parent root;
    private Rectangle rectangle;

    public ViewHandler(final Stage stage) throws IOException {
        this.stage = stage;
        rectangle = new Rectangle(Config.STANDARD_WIDTH, Config.STANDARD_HEIGHT);
        rectangle.setFill(Textures.BACKGROUND.getImagePattern());
        root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        ((AnchorPane) root).getChildren().add(rectangle);
        scene = new Scene(root);
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(final WindowEvent event) {
				System.exit(0);
			}
        });
        this.stage.setScene(scene);
        this.stage.setFullScreen(false);
        //this.stage.setFullScreen(true);
        this.graphicComponents = new ArrayList<>();
    }

    public void removeGraphicComponent(final GraphicComponent graphic) {
        ((AnchorPane) scene.getRoot()).getChildren().filtered(i ->
        (graphic).getNode().equals(i)).get(0).setVisible(false);
        //remove(((EnemyShooterGraphicComponent) graphic).getNode());
        this.graphicComponents.remove(graphic);
    }

    public void setLevelScene() throws IOException {
    	root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/SimpleGui.fxml"));
    	scene.setRoot(root);
//    	this.stage.setFullScreen(true);
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
