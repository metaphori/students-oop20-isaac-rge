package ryleh.view;

import java.io.IOException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewHandler {

    private Stage stage;
    private List<GraphicComponent> graphicComponents;

    public ViewHandler(final Stage stage) throws IOException {
        this.stage = stage;
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/SimpleGui.fxml"));
        Scene scene = new Scene(root);
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
        });
        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
    }

    public Stage getStage() {
		return stage;
	}

	public void render(String id) {
        // TODO Auto-generated method stub
        this.graphicComponents.forEach(i -> i.render());
    }

}
