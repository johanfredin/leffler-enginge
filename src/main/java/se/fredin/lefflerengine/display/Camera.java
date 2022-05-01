package se.fredin.lefflerengine.display;

import se.fredin.lefflerengine.Entity;
import se.fredin.lefflerengine.object.GameObject;

import java.awt.*;
import java.util.Optional;

public class Camera implements Entity {

    private final GamePanel gp;
    public float x, y;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<GameObject> objectOfInterest;

    public Camera(GamePanel gp, GameObject objectOfInterest) {
        this.x = this.y = 0;
        this.gp = gp;
        this.objectOfInterest = Optional.of(objectOfInterest);
    }

    @Override
    public void tick(float deltaTime) {
        if (objectOfInterest.isPresent()) {
            var obj = objectOfInterest.get();
            this.x = obj.x - (float)(gp.screenWidth >> 1);
            this.y = obj.y - (float)(gp.screenHeight >> 1);

            // Handle clamping
            if (x <= 0) {
                x = 0;
            }
            if (y <= 0) {
                y = 0;
            }
            if (x + gp.screenWidth >= gp.tileMap.getWidth()) {
                x = gp.tileMap.getWidth() - gp.screenWidth;
            }
            if (y + gp.screenHeight >= gp.tileMap.getHeight()) {
                y = gp.tileMap.getHeight() - gp.screenHeight;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // No drawing
    }
}
