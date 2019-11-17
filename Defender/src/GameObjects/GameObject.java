package GameObjects;

import java.awt.*;

class GameObject {
    private Coordinate coordinates;
    private int width, height, speed;
    private Boolean alive;
    private Image image;
    private Rectangle hitbox;

    public GameObject(){
    }

    public GameObject(int x, int y) {
        coordinates.setX(0);
        coordinates.setY(0);
        setSpeed(10);
        alive = true;
        hitbox = new Rectangle(x, y, width, height);
    }

    public GameObject(int x, int y, int speed) {
        coordinates.setX(x);
        coordinates.setY(y);
        setSpeed(speed);
        alive = true;
        hitbox = new Rectangle(x, y, width, height);
    }

    protected void getImageDimensions() {
//        width = image.getWidth();
//        height = image.getHeight();
    }

    protected void loadImage(String imageName) {
//        ImageIcon icon = new ImageIcon(imageName);
//        image = icon.getImage();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Image getImage() {
        return image;
    }

    public void kill() {
        alive = false;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        coordinates.setX(x);
    }

    public void setY(int y) {
        coordinates.setY(y);
    }

    // to be implemented
    public boolean isAlive(){
        return true;
    }

    public void move() {

    }

    public void draw() {

    }

}

class Coordinate {
    private int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}