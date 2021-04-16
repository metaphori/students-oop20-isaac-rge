package ryleh.view.menu;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ryleh.common.Config;

public class RylehGameOverMenu {
    private final Stage primaryStage;
    private final Image gameOverImage;

    public RylehGameOverMenu(final Stage primaryStage, final boolean victory) {
        this.primaryStage = primaryStage;
        final String imagePath = victory ? "/assets/texture/menu/victory.png" : "/assets/texture/menu/gameOver.png";
        this.gameOverImage = new Image(imagePath, 
                (double) Config.STANDARD_WIDTH, (double) Config.STANDARD_HEIGHT, true, true);
    }

    /**
     * Shows the menu into primary stage. 
     */
    public void show() {
        final Pane pane = new Pane();
        final ImageView menu = new ImageView(this.gameOverImage);
        menu.setPreserveRatio(true);
        pane.getChildren().add(menu);
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        pane.setOnMouseClicked((e) -> Platform.exit());
        this.primaryStage.getScene().setRoot(pane);
    }

}
