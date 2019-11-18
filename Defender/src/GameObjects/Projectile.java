package GameObjects;

public class Projectile extends GameObject {
    protected int damage;
    private moveDirection direction;


    public Projectile(int x, int y){
        super(x, y);
        setSpeed(15);
    }

    public int getDamage() {
        return damage;
    }

    public void setDirection(moveDirection direction) {
        this.direction = direction;
    }

    public void move(moveDirection direction){
        switch(direction){
            case LEFT:
                setY(getX() + this.getSpeed());
                break;
            case RIGHT:
                setY(getX() - this.getSpeed());
                break;
        }

        if (this.getX() > 600 || this.getX() < 0 || this.getY() > 500 || this.getY() < 0)
            this.kill();

    }

    public moveDirection getDirection() {
        return direction;
    }
}