package ryleh.core;

import javafx.scene.Parent;
import javafx.stage.Stage;
import ryleh.view.GraphicComponent;
import ryleh.view.ViewHandler;

public class GameEngine {
    private GameState rylehState;
    private long period = 20;
    private boolean running = true;

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
        	long initialTime = System.nanoTime();
        	final double UPS = 60;
        	final double FPS = 60;
        	final double timeU = 1000000000 / UPS;
        	final double timeF = 1000000000 / FPS;
        	double deltaU = 0, deltaF = 0;
        	int frames = 0, ticks = 0;
        	long timer = System.currentTimeMillis();

        	    while (running) {

        	        long currentTime = System.nanoTime();
        	        deltaU += (currentTime - initialTime) / timeU;
        	        deltaF += (currentTime - initialTime) / timeF;
        	        initialTime = currentTime;

        	        if (deltaU >= 1) {
        	        	rylehState.updateState(deltaU);
        	            ticks++;
        	            deltaU--;
        	        }

        	        if (deltaF >= 1) {
        	            rylehState.updateRender(deltaF);
        	            frames++;
        	            deltaF--;
        	        }
        	    }
//            long lastTime = System.currentTimeMillis();
//            while (!rylehState.isGameOver()) {
//                final long current = System.currentTimeMillis();
//                final int elapsed = (int) (current - lastTime);
//                rylehState.updateState(elapsed);
//                waitForNextFrame(current);
//                lastTime = current;
//            }
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
