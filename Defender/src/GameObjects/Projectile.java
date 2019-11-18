package GameObjects;

public class Projectile extends GameObject {
    private int damage;
    private moveDirection direction;


    public Projectile(){
        loadImage("res/Icons/004-rocket-1.png");
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
    }

}