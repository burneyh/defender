package GameObjects;

public class Alien extends GameObject {
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
        return 0;
    }



}
