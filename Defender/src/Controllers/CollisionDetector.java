package Controllers;


import GameObjects.*;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;


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
                if(!motherShip.getInvincible())
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
                if (motherShip.isAlive() && projectile.isAlive() && projectileBox.getBoundsInParent().intersects(motherBox.getBoundsInParent()) && !motherShip.getInvincible()) {
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

    private void checkShipCollisionWithPowerUps(MotherShip motherShip, ArrayList<PowerUp> powerUps) {
        Rectangle motherBox = motherShip.getHitbox();
        for(PowerUp powerUp: powerUps){
            Rectangle powerBox = powerUp.getHitbox();
            if (motherShip.isAlive() && powerUp.isAlive() && motherBox.getBoundsInParent().intersects(powerBox.getBoundsInParent())){
                motherShip.setPowerUp(powerUp);

                // should they be killed?
                powerUp.kill();

                break;
            }
        }
    }

    private void checkProjectileCollisionWithAlien(ArrayList<Alien> aliens, ArrayList<Projectile>  projectiles){
        ArrayList<Explosion> explosions = new ArrayList<>();

        for(Alien alien: aliens) {
            Rectangle alienBox = alien.getHitbox();
            for (Projectile projectile : projectiles) {
                if ((projectile instanceof ShipProjectile)) {
                    Rectangle projectileBox = projectile.getHitbox();

                    // if the projectile is the explosive, then make it's rectangle bigger so that aliens around will die
                    if (alien.isAlive() && projectile.isAlive() && projectileBox.getBoundsInParent().intersects(alienBox.getBoundsInParent())) {
                        alien.kill();

                        if(projectile.getExplosive())
                            explosions.add(new Explosion(projectile.getX(), projectile.getY()));

                        if (!projectile.getInvincible())
                            projectile.kill();

                        break;
                    }
                }
            }
        }

        for(Explosion explosion: explosions) {
            Rectangle explosionBox = explosion.getHitbox();
            for (Alien alien : aliens) {
                Rectangle alienBox = alien.getHitbox();
                if (alien.isAlive() && explosionBox.getBoundsInParent().intersects(alienBox.getBoundsInParent())) {
                    alien.kill();
                }
            }
        }
        explosions.clear();
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

    public void checkAllCollisions(MotherShip motherShip, ArrayList<Alien> aliens, ArrayList<Human> humans, ArrayList<Projectile>  projectiles, ArrayList<PowerUp> powerUps){
        checkShipCollisionsWithAlien(motherShip, aliens);
        checkProjectileCollisionsWithShip(motherShip, projectiles);
        checkMutation(aliens, humans);
        checkProjectileCollisionWithAlien(aliens, projectiles);
        checkShipCollisionWithPowerUps(motherShip, powerUps);
    }

}