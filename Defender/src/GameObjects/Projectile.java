package GameObjects;

public class Projectile extends GameObject {
    private int damage;

    public Projectile(){
        loadImage("res/Icons/004-rocket-1.png");
        getImageDimensions();
    }

    public int getDamage() {
        return damage;
    }
}