package GameObjects;

import UserInterface.MyApplication;

public class Human extends GameObject{
    public Human(){
        super((int)(Math.random() * ( 3 * MyApplication.WIDTH - 20)) - MyApplication.WIDTH, 485);
        loadImage("Icons/003-workers.png");
        getImageDimensions();
    }

}
