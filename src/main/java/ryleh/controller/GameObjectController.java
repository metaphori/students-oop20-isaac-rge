package ryleh.controller;

import ryleh.model.GameObjectImpl;
import ryleh.view.View;

public class GameObjectController {

    private View view;
    private GameObjectImpl gameObject;

    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }
    public GameObjectImpl getGameObject() {
        return gameObject;
    }
    public void setGameObject(GameObjectImpl gameObject) {
        this.gameObject = gameObject;
    }
}
