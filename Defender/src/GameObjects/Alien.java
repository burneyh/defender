package GameObjects;
import UserInterface.MyApplication;


import java.util.Random;

public class Alien extends GameObject {
    protected double moveConstTime = 200;


    int points;

    public Alien(){
        super(((int)(Math.random() * 500)), (int)(Math.random() * 400));
        setSpeed(5);
        move();
    }
    public Alien(int x,int  y){
        super(x,y);
        setSpeed(5);
        move();

    }
    public int getScore() {
        return points;
    }

    public void move(){
        Random direction = new Random();
        int ans = direction.nextInt(4) + 1;
        double currentTime = System.currentTimeMillis();
        double movedTime = currentTime;
        if((currentTime - movedTime) >= moveConstTime){
            if(ans == 1 && getX()< MyApplication.WIDTH )
                move(moveDirection.RIGHT);
            else if (ans == 1 && getX() == MyApplication.WIDTH)
                move(moveDirection.LEFT);
            else if (ans == 2 && getY() > 0)
                move(moveDirection.UP);
            else if (ans == 2 && getY() == 0)
                move(moveDirection.DOWN);
            else if (ans == 3 && getX() > 0)
                move(moveDirection.LEFT);
            else if (ans == 3 && getX() == 0)
                move(moveDirection.RIGHT);
            else if (ans == 4 && getY() < MyApplication.HEIGHT)
                move(moveDirection.DOWN);
            else if (ans == 4 && getX() == MyApplication.HEIGHT)
                move(moveDirection.UP);
        }
        movedTime = currentTime;
    }




}
