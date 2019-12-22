package GameObjects;

import UserInterface.MyApplication;

public class Projectile extends GameObject {
    protected int damage;
    private moveDirection direction;
    public int oblique;

    public Projectile(int x, int y){
        super(x, y);
        setSpeed(7);
        oblique = 2;
    }


    public void move(moveDirection direction){
        switch(direction){
            case LEFT:
                setX(getX() - this.getSpeed());
                break;
            case RIGHT:
                setX(getX() + this.getSpeed());
                break;
        }

        if (this.getX() > MyApplication.WIDTH + 50|| this.getX() < -50 || this.getY() > 500 || this.getY() < 0)
            this.kill();

    }

    public int getDamage() {
        return damage;
    }
    public void setDirection(moveDirection direction) {
        this.direction = direction;
    }

    public moveDirection getDirection() {
        return direction;
    }
}