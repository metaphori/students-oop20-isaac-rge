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

public class MenuFactoryImpl implements MenuFactory {
	
	private static final int SIZE = 40;
    private int scaledSize = (int) (Config.SCALE_MODIFIER * SIZE);
    private Font levelFont;
    private Color startColor;
    private Color hoverColor;
    private Text description;

    public MenuFactoryImpl() {
        description = new Text("");
        hoverColor = Color.CADETBLUE;
        startColor = Color.CORNFLOWERBLUE;
    }

    public MenuFactoryImpl(final int scale) {
        this();
        this.scaledSize = (int) (Config.SCALE_MODIFIER * scale);
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public void createCustomAlert(final String text) {
        final Stage window = new Stage();
        final HBox confirm = new HBox(scaledSize);
        final VBox container = new VBox();
        final Text question = new Text(text);
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
    /**
     * {@inheritDoc}
     */
    @Override
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
    /**
     * Sets some properties and mouse events of the given text.
     * @param text The text that has to be initialized
     * @param description The description to bind to the text
     * @param action The action to run when the mouse is clicked
     */
	private void createCustomText(final Text text, final String description, final Runnable action) {
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
	/**
	 * Sets some properties of the given rectangle.
	 * @param Rectangle is the side rectangle 
	 * @param text The text at which the rectangle has to be binded
	 */
	private void createSideRectangle(final Rectangle rectangle, final Text text) {
        rectangle.setFill(hoverColor);
        rectangle.setVisible(false);
        rectangle.visibleProperty().bind(
                        Bindings.when(text.hoverProperty()).then(true).otherwise(false));
    }
	/**
     * {@inheritDoc}
     */
	@Override
	public int getScaledSize() {
		return scaledSize;
	}
	/**
     * {@inheritDoc}
     */
	@Override
	public Color getStartColor() {
		return startColor;
	}
	/**
     * {@inheritDoc}
     */
	@Override
	public Color getHoverColor() {
		return hoverColor;
	}
	/**
     * {@inheritDoc}
     */
	@Override
	public Text getDescription() {
		return description;
	}
	/**
     * {@inheritDoc}
     */
	@Override
	public void setLevelFont(final Font levelFont) {
		this.levelFont = levelFont;
	}
}