package GameObjects;

public class Human extends GameObject{
    public Human(){
        super();
        this.setX(5);
        this.setY(5);
        loadImage("Icons/003-workers.png");
        getImageDimensions();
    }

    public boolean isAlive(){
        return true;
    }
}
