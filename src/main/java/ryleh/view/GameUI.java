package ryleh.view;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ryleh.Ryleh;

public class GameUI {
    /**
     * Duration of fading transition for item effect text.
     */
    private static final double FT_DURATION = 4000;
    /**
     * Screen width ratio value.
     */
    private static final int SCREEN_WIDTH_RATIO = 16;
    /**
     * Screen height ratio value.
     */
    private static final int SCREEN_HEIGHT_RATIO = 9;
    /**
     * Pixel correction on X,Y coordinates of Text objects.
     */
    private static final int PIXEL_CORRECTION = 75;
    /**
     * Font scale.
     */
    private static final int FONT_SCALE = 37;
    private Text lives;
    private Text level;
    private Text item;
    private Text tutorialCommands;
    private Text tutorialObjective;
    private final List<Text> tutorialUi;
    private final Font font;

    /**
     * Creates a new Instance of GameUI.
     */
    public GameUI() {
        this.tutorialUi = new ArrayList<>();
        this.font = Font.loadFont(Ryleh.class.getResource("/assets/fonts/manaspc.ttf").toExternalForm(),
                FONT_SCALE * ViewHandlerImpl.SCALE_MODIFIER);
        this.setLives();
        this.setLevel();
        this.setTutorial();
        this.setItemPickUp();
    }

    /**
     * A method to set the text that represents the current level.
     */
    private void setLevel() {
        this.level = new Text();
        this.level.setFont(this.font);
        this.level.setX((ViewHandlerImpl.STANDARD_WIDTH / SCREEN_WIDTH_RATIO) * 2
                + PIXEL_CORRECTION * ViewHandlerImpl.SCALE_MODIFIER);
        this.level.setY((ViewHandlerImpl.STANDARD_HEIGHT / SCREEN_HEIGHT_RATIO) * 1);
        this.level.setFill(Color.WHITE);
    }

    /**
     * A method to set the tutorial text that will appear on the first level.
     */
    private void setTutorial() {
        this.tutorialCommands = new Text(
                "List of commands:\n W -> move forward \n A -> move left \n S -> move downward \n D -> move right \n Spacebar -> shoot");
        this.tutorialCommands.setFont(this.font);
        this.tutorialCommands.setX((ViewHandlerImpl.STANDARD_WIDTH / SCREEN_WIDTH_RATIO) * 3
                + PIXEL_CORRECTION * ViewHandlerImpl.SCALE_MODIFIER);
        this.tutorialCommands.setY((ViewHandlerImpl.STANDARD_HEIGHT / SCREEN_HEIGHT_RATIO) * 3);
        this.tutorialCommands.setFill(Color.WHITE);
        this.tutorialObjective = new Text(
                "In order to win you must \nbeat level 30.\nAfter defeating all \nenemies on a level a \ndoor will open and bring \nyou to the next level.\n"
                        + "Every 3 levels an \nitem will spawn.\n"
                        + "If you lose all your \nlives you will lose.\nGood luck and have fun!");
        this.tutorialObjective.setFont(this.font);
        this.tutorialObjective.setX((ViewHandlerImpl.STANDARD_WIDTH / SCREEN_WIDTH_RATIO) * 8
                + PIXEL_CORRECTION * ViewHandlerImpl.SCALE_MODIFIER);
        this.tutorialObjective.setY((ViewHandlerImpl.STANDARD_HEIGHT / SCREEN_HEIGHT_RATIO) * 2);
        this.tutorialObjective.setFill(Color.WHITE);
        this.tutorialUi.add(this.tutorialCommands);
        this.tutorialUi.add(this.tutorialObjective);
    }

    /**
     * A method to set the text that represents the current item effect.
     */
    private void setItemPickUp() {
        this.item = new Text();
        this.item.setFont(this.font);
        this.item.setX((ViewHandlerImpl.STANDARD_WIDTH / SCREEN_WIDTH_RATIO) * 2);
        this.item.setY((ViewHandlerImpl.STANDARD_HEIGHT / SCREEN_HEIGHT_RATIO) * 8);
        this.item.setFill(Color.WHITE);
        this.item.setVisible(false);
    }

    /**
     * A method to set the text that represents the remaining lives.
     */
    private void setLives() {
        this.lives = new Text("Lives: 3");
        this.lives.setFont(this.font);
        this.lives.setX((ViewHandlerImpl.STANDARD_WIDTH / SCREEN_WIDTH_RATIO) * 11
                + PIXEL_CORRECTION * ViewHandlerImpl.SCALE_MODIFIER);
        this.lives.setY((ViewHandlerImpl.STANDARD_HEIGHT / SCREEN_HEIGHT_RATIO) * 1);
        this.lives.setFill(Color.WHITE);
    }

    /**
     * A method that sets visibility of item to true and plays a fading transition
     * for the item effects text.
     */
    public void playFt() {
        this.item.setVisible(true);
        final FadeTransition ft = new FadeTransition(Duration.millis(FT_DURATION), this.item);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    /**
     * A method that returns the number of remaining lives Text.
     * 
     * @return the number of remaining lives.
     */
    public Text getLives() {
        return this.lives;
    }

    /**
     * A method that returns the last item effect Text.
     * 
     * @return the number of remaining lives.
     */
    public Text getItemPickUp() {
        return this.item;
    }

    /**
     * A method that returns the current level Text.
     * 
     * @return the current level number.
     */
    public Text getLevel() {
        return this.level;
    }

    /**
     * A method that returns the tutorial Ui elements Texts.
     * 
     * @return the current level number.
     */
    public List<Text> getTutorial() {
        return this.tutorialUi;
    }
}
