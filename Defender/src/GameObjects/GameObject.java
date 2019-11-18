package GameObjects;
import java.awt.*;
//import javafx.scene.image.*;
//import javafx.scene.shape.Rectangle;
import javax.swing.ImageIcon;




class GameObject {
    public final static  int RIGHT = 0;
    public final static int LEFT = 1;

    private Coordinate coordinates;
    private int width, height, speed;
    private boolean alive;
    private Image image;
    private Rectangle hitbox;

    public GameObject(){

    }

    public GameObject(int x, int y) {
        coordinates.setX(x);
        coordinates.setY(y);
        setSpeed(10);
        alive = true;

    }

    public GameObject(int x, int y, int speed) {
        coordinates.setX(x);
        coordinates.setY(y);
        setSpeed(speed);
        alive = true;
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
        hitbox = new Rectangle(coordinates.getX(), coordinates.getY(), width, height);
    }

    protected void loadImage(String imageName) {
        ImageIcon icon = new ImageIcon(imageName);
        image = icon.getImage();

    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Coordinate getCoordinates(){
        return coordinates;
    }
    public Image getImage() {
        return image;
    }

    public void kill() {
        alive = false;
    }
    public boolean isAlive(){
        return alive;
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

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    // to be implemented
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
    public int getX(){return x;}
    public int getY(){return y;}
}

