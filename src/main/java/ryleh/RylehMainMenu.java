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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ryleh.common.Config;
import ryleh.core.GameEngine;

public class RylehMainMenu {
	private final double width = Config.STANDARD_WIDTH;
	private final double height =Config.STANDARD_HEIGHT;
	
	//private final double width = Screen.getPrimary().getBounds().getWidth();
	//private final double height = Screen.getPrimary().getBounds().getHeight();
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
		this.box.getChildren().add(createCustomButton("Start Game", "Start a new adventure", () -> {
			final GameEngine engine = new GameEngine();
			
			
			primaryStage.setHeight(height);
			primaryStage.setWidth(width);
			 
			 
			engine.initGame(primaryStage);
			engine.mainLoop();
		}));
		this.box.getChildren().add(createCustomButton("Options", "Change in game options", null));
		this.box.getChildren().add(createCustomButton("Quit Game", "Exit to desktop", () -> {
			createCustomAlert();
		}));
		this.separator.setOrientation(Orientation.HORIZONTAL);
		this.separator.setTranslateX(font / 2);
		this.description.setFont(new Font(font));
		this.description.setFill(startColor);
		this.description.setTranslateY(separator.getTranslateY() + 10);
		this.box.setAlignment(Pos.CENTER_LEFT);
		this.box.setTranslateX(font);
		this.box.getChildren().addAll(this.separator, this.description);
		this.pane.setLeft(this.box);
		primaryStage.setScene(new Scene(this.pane, width, height));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Incredibile Funziona");
		primaryStage.show();
	}
	private void createCustomAlert() {
		Stage window = new Stage();
		HBox confirm = new HBox(font);
		VBox container = new VBox();
		Text question = new Text("Do you really want to quit this beatiful game?");
		question.setFont(new Font(font));
		confirm.getChildren().add(createCustomButton("YES", "", () -> System.exit(0)));
		confirm.getChildren().add(createCustomButton("NO", "", () -> window.close()));
		confirm.setAlignment(Pos.CENTER);
		container.getChildren().add(question);
		container.getChildren().add(confirm);
		container.setAlignment(Pos.CENTER);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Quit Game");
		window.setWidth(this.width / 3);
		window.setHeight(this.height / 3);
		window.setScene(new Scene(container));
		window.setResizable(false);
		window.showAndWait();
	}
	private Node createCustomButton(final String name, final String description, final Runnable action) {
		HBox hbox = new HBox(name.length());
		Rectangle side = new Rectangle(font / 4, font);
		Text btnText = new Text(name);
		createCustomText(btnText, description, action);
		createSideRectangle(side, btnText);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().addAll(side, btnText);
		return hbox;
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
