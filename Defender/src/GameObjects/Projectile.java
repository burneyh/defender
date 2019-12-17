package GameObjects;

public class Projectile extends GameObject {
    protected int damage;
    private moveDirection direction;


    public Projectile(int x, int y){
        super(x, y);
        setSpeed(5);
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
                setX(getX() - this.getSpeed());
                break;
            case RIGHT:
                setX(getX() + this.getSpeed());
                break;
        }

        if (this.getX() > 600 || this.getX() < 0 || this.getY() > 500 || this.getY() < 0)
            this.kill();

    }

    public void move(moveDirection direction, boolean oblique){
        switch(direction){
            case LEFT:
                setX(getX() - this.getSpeed());
                break;
            case RIGHT:
                setX(getX() + this.getSpeed());
                break;
        }

        if(oblique) {
            setY(getY() - this.getSpeed());
        }
        else {
            setY(getY() + this.getSpeed());
        }

        if (this.getX() > 600 || this.getX() < 0 || this.getY() > 500 || this.getY() < 0)
            this.kill();

    }

    public moveDirection getDirection() {
        return direction;
    }
}