package ryleh;


import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.font.FontFactory;

import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ryleh.core.GameEngine;

public class RylehMainMenu {

//	private final double width = 
//	private final double height = Screen.getPrimary().getVisualBounds().getHeight();
	private final int font = (int) (30);
	private final Color startColor = Color.CORNFLOWERBLUE;
	private final Color hoverColor = Color.CADETBLUE;
	private Text description = new Text("");
	private List<Node> nodes = new ArrayList<>();
	private static FontFactory levelFont;
	private VBox box = new VBox();
	private Separator separator = new Separator();
	private BorderPane pane = new BorderPane();
	
	public RylehMainMenu(final Stage primaryStage) {
		
		//levelFont = FXGL.getAssetLoader().loadFont("manaspc.ttf");
		createCustomButton("Start Game", "Start a new adventure", () -> {
			final GameEngine engine = new GameEngine();
			engine.initGame(primaryStage);
			engine.mainLoop();
		});
		createCustomButton("Options", "Change in game options", null);
		createCustomButton("Quit Game", "Exit to desktop", () -> {
			System.exit(0);
		});
		separator.setOrientation(Orientation.HORIZONTAL);
		separator.setTranslateX(font / 2);
		description.setFont(Font.font(font));
		description.setFill(startColor);
		description.setTranslateY(separator.getTranslateY() + 10);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setTranslateX(font);
		box.getChildren().addAll(this.nodes);
		box.getChildren().addAll(separator, description);
		pane.setLeft(box);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();
	}
	private void createCustomButton(final String name, final String description, final Runnable action) {
		HBox hbox = new HBox(font / 2);
		Rectangle side = new Rectangle(font / 4, font);
		Text text = new Text(name);
		createCustomText(text, description, action);
		createSideRectangle(side, text);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().addAll(side, text);
		this.nodes.add(hbox);
	}
	private void createCustomText(final Text text, final String description, final Runnable action) {
		text.setFont(Font.font(font));
		text.setTextAlignment(TextAlignment.LEFT);
		text.setFill(startColor);
		text.setSelectionFill(hoverColor);
		text.setOnMouseEntered(event -> {
			text.setFill(hoverColor);
			this.description.setText(description);
		});
		text.setOnMouseExited(event -> {
			this.description.setText("");
			text.setFill(startColor);
		});
		text.setOnMouseClicked(event -> {
			action.run();
		});
	}
	private void createSideRectangle(final Rectangle rectangle, final Text text) {
		rectangle.setFill(hoverColor);
		rectangle.setVisible(false);
		rectangle.visibleProperty().bind(
				Bindings.when(text.hoverProperty()).then(true).otherwise(false));
	}
}
