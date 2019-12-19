package GameObjects;

public class PowerUp extends GameObject {
    public static enum Type {REFILL_HEALTH, SHIELD, TRIPLE_SHOT, EMPOWERED_SHOT, EXPLOSIVE_SHOT, FROST}

    private Type type;

    public PowerUp(int x, int y, Type type) {
        super(x, y);
        this.type = type;
        switch(type){
            case REFILL_HEALTH:
                this.loadImage("Icons/fillHealthPower.png");
                break;
            case SHIELD:
                this.loadImage("Icons/shieldPower.png");
                break;
            case TRIPLE_SHOT:
                this.loadImage("Icons/tripleShot.png");
                break;
            case EMPOWERED_SHOT:
                this.loadImage("Icons/empoweredShot.png");
                break;
            case EXPLOSIVE_SHOT:
                this.loadImage("Icons/explosiveShot.png");
                break;
            case FROST:
                this.loadImage("Icons/freeze.png");
                break;

        }

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
