package GameObjects;

public class Projectile extends GameObject {
    private int damage;
    private moveDirection direction;


    public Projectile(){
        loadImage("Icons/001-rocket.png");
        getImageDimensions();
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

}