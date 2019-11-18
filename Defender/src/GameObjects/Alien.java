package GameObjects;
import UserInterface.MyApplication;

import java.util.MATH;
import java.util.Random;

public class Alien extends GameObject {
    protected double moveConstTime = 200;


    int points;

    public Alien(){
        super(((int)(Math.random() * 500)), (int)(Math.random()) * 400);
        setSpeed(5);
    }
    public Alien(int x,int  y){
        super(x,y);
        setSpeed(5);
    }
    public int getScore() {
        return points;
    }

    public void move(){
        Random direction= new Random();;
        int ans = direction.nextInt(4) + 1;
        double currentTime = System.currentTimeMillis();
        double movedTime = currentTime;
        if((currentTime - movedTime) >= moveConstTime){
            if(ans == 1 && getX()< MyApplication.WIDTH )
                move(RIGHT);
            else if (ans == 1 && getX() = MyApplication.WIDTH)
                move(LEFT);
            else if (ans == 2 && getY() > 0)
                move(UP);
            else if (ans == 2 && getY() = 0)
                move(Down);
            else if (ans == 3 && getX() > 0)
                move(LEFT);
            else if (ans == 3 && getX() = 0)
                move(Right);
            else if (ans == 4 && getY() < MyApplication.HEIGHT)
                move(DOWN);
            else if (ans == 4 && getX() = MyApplication.HEIGHT)
                move(UP);
        }
        movedTime = currentTime;
    }




}
