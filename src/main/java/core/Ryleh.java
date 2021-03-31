package core;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Ryleh extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Incredibile Funziona");
        final Button btn = new Button();
        btn.setText("Bestemmia");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(final ActionEvent event) {
                System.out.println("**bestemmione**");
            }
        });
        final StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
}