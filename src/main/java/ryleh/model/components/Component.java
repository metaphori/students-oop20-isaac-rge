package ryleh.model.components;

import ryleh.model.GameObject;

public interface Component {

    void onAdded(GameObject object);

    void onUpdate(double deltaTime);

}