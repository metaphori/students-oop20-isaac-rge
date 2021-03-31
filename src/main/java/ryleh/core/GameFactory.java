package ryleh.core;


public class GameFactory {
     private static GameFactory instance;

     private GameFactory() { }

     public static GameFactory getInstance(){
            if (instance == null) {
                    instance = new GameFactory();
            }
            return instance;
    }
}
