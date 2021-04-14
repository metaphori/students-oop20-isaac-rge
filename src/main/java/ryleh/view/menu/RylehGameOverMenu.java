package ryleh.view.menu;

import javax.swing.GroupLayout.Alignment;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ryleh.Ryleh;
import ryleh.common.Config;

public class RylehGameOverMenu {
    private Stage primaryStage;
    private final Font font;

    public RylehGameOverMenu(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        font = Font.loadFont(Ryleh.class.getResource("/assets/fonts/manaspc.ttf")
                .toExternalForm(), Config.SCALE_MODIFIER);
    }

    public void show() {
        final Pane pane = new Pane();
        final Text text = new Text();
        text.setFont(font);
        text.setText("GAME OVER");
        text.setX(Config.STANDARD_WIDTH / 2);
        text.setY(Config.STANDARD_HEIGHT / 2);
        text.setFill(Color.RED);
        text.setScaleX(Config.SCALE_MODIFIER * 100);
        text.setScaleY(Config.SCALE_MODIFIER * 100);
        text.setTextAlignment(TextAlignment.CENTER);
        //final HBox button = new HBox();
        //button.setOnMouseClicked((e) -> Platform.exit());
        final Text exit = new Text();
        exit.setFont(font);
        exit.setText("Click here to exit the game");
        exit.setX(Config.STANDARD_WIDTH / 2);
        exit.setY(Config.STANDARD_HEIGHT / 2 - Config.STANDARD_HEIGHT / 6);
        exit.setFill(Color.WHITE);
        exit.setScaleX(Config.SCALE_MODIFIER * 30);
        exit.setScaleY(Config.SCALE_MODIFIER * 30);
        exit.setTextAlignment(TextAlignment.CENTER);
        exit.setX(Config.STANDARD_WIDTH / 2);
        exit.setY(Config.STANDARD_HEIGHT / 2 + Config.STANDARD_HEIGHT/5);
        exit.setOnMouseClicked((e) -> Platform.exit());
        //button.getChildren().add(exit);
        pane.getChildren().addAll(text, exit);
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        this.primaryStage.getScene().setRoot(pane);
    }

}
