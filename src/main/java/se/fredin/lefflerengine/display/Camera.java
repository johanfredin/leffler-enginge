package se.fredin.lefflerengine.display;

import se.fredin.lefflerengine.GameEntity;
import se.fredin.lefflerengine.object.GameObject;

import java.awt.*;
import java.util.Optional;

public class Camera implements GameEntity {

    public float x, y;
    public int viewPortWidth, viewPortHeight;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<GameObject> objectOfInterest;

    public Camera(int viewPortWidth, int viewPortHeight, GameObject objectOfInterest) {
        this.x = this.y = 0;
        this.viewPortWidth = viewPortWidth;
        this.viewPortHeight = viewPortHeight;
        this.objectOfInterest = Optional.of(objectOfInterest);
    }

    @Override
    public void tick(float deltaTime) {
//        if (objectOfInterest.isPresent()) {
//            var obj = objectOfInterest.get();
//            this.x = obj.x - (float)(gp.screenWidth >> 1);
//            this.y = obj.y - (float)(gp.screenHeight >> 1);
//
//            // Handle clamping
//            if (x <= 0) {
//                x = 0;
//            }
//            if (y <= 0) {
//                y = 0;
//            }
//            if (x + gp.screenWidth >= gp.tileMap.getWidth()) {
//                x = gp.tileMap.getWidth() - gp.screenWidth;
//            }
//            if (y + gp.screenHeight >= gp.tileMap.getHeight()) {
//                y = gp.tileMap.getHeight() - gp.screenHeight;
//            }
//        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // No drawing
    }
}
