package GameObjects;

public class PowerUp extends GameObject {
    public static enum Type {REFILL_HEALTH, SHIELD, TRIPLE_SHOT, EMPOWERED_SHOT, EXPLOSIVE_SHOT, FROST}

    private Type type;

    public PowerUp(int x, int y, Type type) {
        super(x, y);
        this.type = type;
        this.loadImage("Icons/003-workers.png");
        this.getImageDimensions();
        this.move();
    }

    public void move() {
        this.setY(this.getY() + 1);
        if (this.getY() > 500)
            this.kill();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
