package ryleh.view.menu;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ryleh.common.Config;

public class RylehGameOverMenu {
    private final Stage primaryStage;

    public RylehGameOverMenu(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Shows the menu into primary stage. 
     */
    public void show() {
        final Pane pane = new Pane();
        final ImageView menu = new ImageView(
                new Image("/assets/texture/menu/gameOver.png", 
                        (double) Config.STANDARD_WIDTH, (double) Config.STANDARD_HEIGHT, true, true));
        menu.setPreserveRatio(true);
        pane.getChildren().add(menu);
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        pane.setOnMouseClicked((e) -> Platform.exit());
        this.primaryStage.getScene().setRoot(pane);
    }

}
