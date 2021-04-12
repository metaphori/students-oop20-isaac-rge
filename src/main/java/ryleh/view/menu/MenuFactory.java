package ryleh.view.menu;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;
import ryleh.common.Config;

public class MenuFactory {
    public int scaledSize = (int) (Config.SCALE_MODIFIER * 30);
    public Font levelFont;
    public Color startColor;
    public Color hoverColor;
    public Text description;
    public VBox box;
    public Separator separator;
    public BorderPane pane;

    public MenuFactory() {
        separator = new Separator();
        pane = new BorderPane();
        box = new VBox();
        description = new Text("");
        hoverColor = Color.CADETBLUE;
        startColor = Color.CORNFLOWERBLUE;
    }

    public MenuFactory(final int scale) {
        this();
        this.scaledSize = (int) (Config.SCALE_MODIFIER * scale);
    }

    public void createCustomAlert() {
        final Stage window = new Stage();
        final HBox confirm = new HBox(scaledSize);
        final VBox container = new VBox();
        final Text question = new Text("Do you really want to quit?");
        question.setFont(new Font(scaledSize));
        confirm.getChildren().add(createCustomButton("YES", "", () -> Platform.exit()));
        confirm.getChildren().add(createCustomButton("NO", "", () -> window.close()));
        confirm.setAlignment(Pos.CENTER);
        container.getChildren().add(question);
        container.getChildren().add(confirm);
        container.setAlignment(Pos.CENTER);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Quit Game");
        window.setWidth(Config.STANDARD_WIDTH / 3);
        window.setHeight(Config.STANDARD_HEIGHT / 3);
        window.setScene(new Scene(container));
        window.setResizable(false);
        window.showAndWait();
    }
    public Node createCustomButton(final String name, final String description, final Runnable action) {
        final HBox hbox = new HBox(name.length());
        final Rectangle side = new Rectangle(scaledSize / 4, scaledSize);
        final Text btnText = new Text(name);
        createCustomText(btnText, description, action);
        createSideRectangle(side, btnText);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(side, btnText);
        return hbox;
    }
    public void createCustomText(final Text text, final String description, final Runnable action) {
        text.setFont(levelFont);
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
    public void createSideRectangle(final Rectangle rectangle, final Text text) {
        rectangle.setFill(hoverColor);
        rectangle.setVisible(false);
        rectangle.visibleProperty().bind(
                        Bindings.when(text.hoverProperty()).then(true).otherwise(false));
    }
}
