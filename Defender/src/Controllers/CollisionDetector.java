package Controllers;


import GameObjects.*;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class CollisionDetector {
    private static CollisionDetector collisionDetector = null;

    private CollisionDetector() {
    }

    // to be updated;
    private void checkShipCollisionsWithAlien(MotherShip motherShip, ArrayList<Alien> aliens) {
        Rectangle motherBox = motherShip.getHitbox();
        for(Alien alien: aliens){
            Rectangle alienBox = alien.getHitbox();
            if (motherShip.isAlive() && alien.isAlive() && motherBox.getBoundsInParent().intersects(alienBox.getBoundsInParent())){
                motherShip.kill();
                alien.kill();
                break;
            }
        }
    }

    private void checkProjectileCollisionsWithShip(MotherShip motherShip, ArrayList<Projectile>  projectiles) {
        Rectangle motherBox = motherShip.getHitbox();

        for(Projectile projectile: projectiles){
                if ( (projectile instanceof AlienProjectile) || (projectile instanceof Mine)) {
                Rectangle projectileBox = projectile.getHitbox();
                if (motherShip.isAlive() && projectile.isAlive() && projectileBox.getBoundsInParent().intersects(motherBox.getBoundsInParent())) {
                    int damage = projectile.getDamage();
                    motherShip.updateHealth(damage);

                    if (motherShip.getHealth() <= 0) {
                        motherShip.kill();
                        break;
                    }
                    projectile.kill();
                }
            }
        }
    }


    private void checkProjectileCollisionWithAlien(ArrayList<Alien> aliens, ArrayList<Projectile>  projectiles){
        for(Alien alien: aliens){
            Rectangle alienBox = alien.getHitbox();
            for(Projectile projectile: projectiles){
                if ( (projectile instanceof ShipProjectile) ) {
                    Rectangle projectileBox = projectile.getHitbox();
                    if (alien.isAlive() && projectile.isAlive() && projectileBox.getBoundsInParent().intersects(alienBox.getBoundsInParent()) && (projectile instanceof ShipProjectile)) {
                        alien.kill();
                        projectile.kill();
                        break;
                    }
                }
            }
        }
    }

    private void checkMutation(ArrayList<Alien> aliens, ArrayList<Human> humans){
        for(Human human: humans){
            Rectangle humanBox = human.getHitbox();
            for(Alien alien: aliens){
                Rectangle alienBox = alien.getHitbox();

                if(alien.isAlive() && human.isAlive() && alienBox.getBoundsInParent().intersects(humanBox.getBoundsInParent())){
                    alien.kill();
                    human.kill();
                    // over for this alien;
                    break;
                }
            }
        }
    }

    public static CollisionDetector getInstance() {
        if (collisionDetector == null)
            collisionDetector = new CollisionDetector();
        return collisionDetector;
    }

    public void checkAllCollisions(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans, ArrayList<Projectile>  projectiles){
        checkShipCollisionsWithAlien(motherShip, aliens);
        checkProjectileCollisionsWithShip(motherShip, projectiles);
        checkMutation(aliens, humans);
        checkProjectileCollisionWithAlien(aliens, projectiles);
    }

}