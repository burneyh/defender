package GameObjects;

public class PowerUp extends GameObject {
    public static enum Type {REFILL_HEALTH, SHIELD, TRIPLE_SHOP, EMPOWERED_SHOT, EXPLOSIVE_SHOT, FROST}

    private Type type;

    public PowerUp(int x, int y, Type type) {
        super(x, y);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
