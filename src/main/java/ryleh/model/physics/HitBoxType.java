package ryleh.model.physics;
/**
 * Enum used to determine hitbox radius of different entities.
 */
public enum HitBoxType {
    PLAYER (70),
    BULLET (5),
    ROCK (75),
    ITEM (30),
    FIRE (45),
    DOOR (75),
    ENEMY (55);
    private  int boxRadius;
    HitBoxType(int radius) {
        boxRadius = radius;
    }
    /**
     * Gets the radius value.
     * @return int value.
     */
    public int getBoxRadius() {
        return boxRadius;
    }
}
