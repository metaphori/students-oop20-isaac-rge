package ryleh;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ryleh.core.GameEngine;
import ryleh.view.menu.RylehMainMenu;
 
public class Ryleh extends Application {
    public static void main(final String[] args) {
       launch(args);
    }
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final RylehMainMenu menu = new RylehMainMenu(primaryStage);
    }
}