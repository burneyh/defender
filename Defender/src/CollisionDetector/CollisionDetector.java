package CollisionDetector;


import GameObjects.*;
import javafx.scene.shape.Rectangle;


public class CollisionDetector {
    private int Score;
    private static CollisionDetector collisionDetector = null;

    private CollisionDetector() {

        Score = 0;
    }

    // to be implemented
    private void updateScore() {
    }


    // to be updated;
    private void checkShipCollisionsWithAllien(MotherShip motherShip, Alien aliens[]) {
        Rectangle motherBox = motherShip.getHitbox();

        for(Alien alien: aliens){
            Rectangle alienBox = alien.getHitbox();


        }
    }

    private void checkProjectileCollisionsWithShip(MotherShip motherShip, Projectile projectile[]) {}

    private void checkMutation(Alien aliens[]){}

    private void checkProjectileCollisionWithAlien(Alien aliens[], Projectile projectiles[]){}

    public CollisionDetector getInstance() {
        if (collisionDetector == null)
            collisionDetector = new CollisionDetector();
        return collisionDetector;
    }

    public void checkAllCollisions(MotherShip motherShip, Alien aliens[], Human humans[], Projectile projectiles[]){
        checkShipCollisionsWithAllien(motherShip, aliens);
        checkProjectileCollisionsWithShip(motherShip, projectiles);
        checkMutation(aliens);
        checkProjectileCollisionWithAlien(aliens, projectiles);
    }

}