package CollisionDetector;

import GameObjects.*;

public class CollisionDetector {
    private int Score;
    private CollisionDetector collisionDetector = null;

    private CollisionDetector() {
        Score = 0;
    }

    public CollisionDetector getInstance() {
        if (collisionDetector == null) {
            collisionDetector = new CollisionDetector();
        }

        return collisionDetector;
    }

    // to be implemented
    private void updateScore() {
        if (checkProjectileCollisionsWithShip()) {
            Score++;
        }
    }

    private Boolean checkShipCollisionsWithAllien(MotherShip motherShip, Aliens aliens[]) {
        return true;
    }

    private Boolean checkProjectileCollisionsWithShip(MotherShip motherShip, Projectile projectile[]) {
        return true;
    }
}