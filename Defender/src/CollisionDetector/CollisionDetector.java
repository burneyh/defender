package CollisionDetector;


import GameObjects.*;
import java.awt.*;
import java.util.LinkedList;


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

            if (motherBox.intersects(alienBox)){
                motherShip.kill();
                alien.kill();
            }
        }
    }

    private void checkProjectileCollisionsWithShip(MotherShip motherShip, Projectile projectiles[]) {
        Rectangle motherBox = motherShip.getHitbox();

        for(Projectile projectile: projectiles){
            Rectangle projectileBox = projectile.getHitbox();
            if(projectileBox.intersects(motherBox)){
                int damage = projectile.getDamage();
                motherShip.updateHealth(damage);

                if(motherShip.getHealth() <= 0){
                    motherShip.kill();
                }
                projectile.kill();
            }
        }
    }


    private void checkProjectileCollisionWithAlien(Alien aliens[], Projectile projectiles[]){
        for(Alien alien: aliens){
            Rectangle alienBox = alien.getHitbox();

            for(Projectile projectile: projectiles){
                Rectangle projectileBox = projectile.getHitbox();
//                if(projectileBox.intersects(alienBox) && (projectile instanceof  ShipProjectile)){
//                    alien.kill();
//                    projectile.kill();
//                }
            }

        }
    }

    private void checkMutation(Alien aliens[]){
    }

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