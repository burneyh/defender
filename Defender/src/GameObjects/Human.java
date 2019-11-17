package GameObjects;

public class Human extends GameObject{
    public Human(){
        loadImage("res/Icons/003-workers.png");
        getImageDimensions();
    }

    public boolean isAlive(){
        return true;
    }
}
