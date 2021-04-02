package ryleh.core;

import javafx.scene.Parent;
import javafx.stage.Stage;
import ryleh.view.GraphicComponent;
import ryleh.view.ViewHandler;

public class GameEngine {
    private GameState rylehState;
    private long period = 20; 

    /*
     * 
     */
    public void initGame(final Stage stage) {
        // TODO Auto-generated method stub
        //this.ryehView = new Scene(stage);
        rylehState = new GameState(stage);
    }

    /*
     * 
     */
    public void mainLoop() {
        new Thread(()-> {
        	while (!rylehState.isGameOver()) {
        		long lastTime;
                final long current = System.currentTimeMillis();
                //final int elapsed = (int)(current - lastTime);
                rylehState.updateState();
                System.out.println("update");
                waitForNextFrame(current);
                lastTime = current;
            }
        }).start();
  
    }

    /*
     * 
     */
    protected void waitForNextFrame(long current){
        final long dt = System.currentTimeMillis() - current;
        if (dt < period){
                try {
                        Thread.sleep(period-dt);
                } catch (Exception ex){}
        }
    }
    
    public static EntityBuilder entityBuilder() {
        return new EntityBuilder();
    }

}
