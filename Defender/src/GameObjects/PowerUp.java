package GameObjects;

public class PowerUp extends GameObject {

    public static enum Type {REFILL_HEALTH, SHIELD, TRIPLE_SHOP, EMPOWERED_SHOT, EXPLOSIVE_SHOT, FROST}

    public PowerUp(int x, int y) {
        super(x, y);
    }
}
