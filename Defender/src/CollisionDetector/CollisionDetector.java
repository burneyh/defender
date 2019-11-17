package CollisionDetector;

import GameObjects.*;


public CollisionDetector{
    private int Score;
    private CollisionDetector collisionDetector = null;

    private CollisionDetector(){
        Score = 0;
    }

    public CollisionDetector getInstance(){
        if(collisionDetector == null){
            collisionDetector = new CollisionDetector();
        }
        
        return collisionDetector;
    }

    // to be implemented
    private void updateScore(){

    }

    private Boolean checkShipCollisionsWithAllien(Mothership motherShip, Aliens aliens[]){
        
    }

    private Boolean checkProjectileCollisionsWithShip(Mothership motherShip, Projectile projectile[])
}