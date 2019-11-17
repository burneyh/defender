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
    private void updateScore(int addAmount) {
        Score += addAmount;
    }


    // to be updated;
    private void checkShipCollisionsWithAllien(MotherShip motherShip, Alien aliens[]) {
        Rectangle motherBox = motherShip.getHitbox();
        for(Alien alien: aliens){
            Rectangle alienBox = alien.getHitbox();
            if (motherShip.isAlive() && alien.isAlive() && motherBox.intersects(alienBox)){
                motherShip.kill();
                alien.kill();
            }
        }
    }

    private void checkProjectileCollisionsWithShip(MotherShip motherShip, Projectile projectiles[]) {
        Rectangle motherBox = motherShip.getHitbox();

        for(Projectile projectile: projectiles){
            Rectangle projectileBox = projectile.getHitbox();
            if(motherShip.isAlive() && projectile.isAlive() && projectileBox.intersects(motherBox)){
                int damage = projectile.getDamage();
                motherShip.updateHealth(damage);

                if(motherShip.getHealth() <= 0){
                    motherShip.kill();
                    break;
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
                if(alien.isAlive() && projectile.isAlive() && projectileBox.intersects(alienBox) && (projectile instanceof  ShipProjectile)){
                    alien.kill();
                    projectile.kill();
                    break;
                }
            }
        }
    }

    private void checkMutation(Alien aliens[], Human humans[]){
        for(Human human: humans){
            Rectangle humanBox = human.getHitbox();
            for(Alien alien: aliens){
                Rectangle alienBox = alien.getHitbox();

                if(alien.isAlive() && human.isAlive() && alienBox.intersects(humanBox)){
                    alien.kill();
                    human.kill();
                    // over for this alien;
                    break;
                }
            }
        }
    }

    public CollisionDetector getInstance() {
        if (collisionDetector == null)
            collisionDetector = new CollisionDetector();
        return collisionDetector;
    }

    public void checkAllCollisions(MotherShip motherShip, Alien aliens[], Human humans[], Projectile projectiles[]){
        checkShipCollisionsWithAllien(motherShip, aliens);
        checkProjectileCollisionsWithShip(motherShip, projectiles);
        checkMutation(aliens, humans);
        checkProjectileCollisionWithAlien(aliens, projectiles);
    }

}