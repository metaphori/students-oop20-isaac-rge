package ryleh.model.components;

import ryleh.model.GameObject;
import ryleh.model.World;

public abstract class Component {

    protected String id;
    protected GameObject object;
    protected World world;

    public Component(World world) {
        super();
        this.world = world;
        id = world.generateId("component");
    }
    public void onAdded(final GameObject object) {
        this.object = object;
    }

    public void onUpdate(final int dt) {
        //System.out.println(this + " si sta aggiornando");
    }

    @Override
    public String toString() {
        return "Component [id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((object == null) ? 0 : object.hashCode());
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Component other = (Component) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (object == null) {
            if (other.object != null)
                return false;
        } else if (!object.equals(other.object))
            return false;
        if (world == null) {
            if (other.world != null)
                return false;
        } else if (!world.equals(other.world))
            return false;
        return true;
    }

}
