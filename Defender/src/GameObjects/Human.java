package GameObjects;

import UserInterface.MyApplication;

public class Human extends GameObject{
    public Human(){
        super((int)(Math.random() * (MyApplication.WIDTH -20)), 485);
        loadImage("Icons/003-workers.png");
        getImageDimensions();
    }

}
