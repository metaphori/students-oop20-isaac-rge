package ryleh.model.components;

public abstract class Component {

    private String id = "ciao";

    public void onUpdate() {
        System.out.println(id + " si sta aggiornando");
    }

}
