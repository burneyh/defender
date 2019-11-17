package GameObjects;

public class MotherShip extends GameObject{


    int health;
    int score;
    int direction;
    int left;
    int right;
    int up;
    int down;

    public MotherShip(){
        super(300, 250);
        health = 100;
        score = 0;
        direction = 1;
        left = 0;
        right =0;
        up = 0;
        down = 0;

        loadImage("/Defender/src/UserInterface/Icons/001-rocket.png");
        getImageDimensions();
    }
    public void updateScore(int score){
        this.score+= score;
    }

    public void updateHealth(int damage) {
        health  = health - damage;
    }
    public int getDirection() {
        return direction;
    }
    public MotherShip getinstance() {
        return this;
    }
}
