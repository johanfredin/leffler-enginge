package se.fredin.leffler.engine.display;

import se.fredin.leffler.engine.core.Rendereable;
import se.fredin.leffler.engine.object.GameObjectBase;

import java.awt.*;
import java.util.Optional;

public class Camera implements Rendereable {

    private final int mapWidth;
    private final int mapHeight;
    public float x = 0, y = 0;
    public int viewPortWidth, viewPortHeight;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<GameObjectBase> objectOfInterest;

    public Camera(int viewPortWidth, int viewPortHeight, int mapWidth, int mapHeight, GameObjectBase objectOfInterest) {
        this.viewPortWidth = viewPortWidth;
        this.viewPortHeight = viewPortHeight;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.objectOfInterest = Optional.ofNullable(objectOfInterest);
    }

    @Override
    public void tick(float deltaTime) {
        if (objectOfInterest.isPresent()) {
            var obj = objectOfInterest.get();
            this.x = obj.x - (float)(viewPortWidth >> 1);
            this.y = obj.y - (float)(viewPortHeight >> 1);

            // Handle clamping
            if (x <= 0) {
                x = 0;
            }
            if (y <= 0) {
                y = 0;
            }
            if (x + viewPortWidth >= mapWidth) {
                x = mapWidth - viewPortWidth;
            }
            if (y + viewPortHeight >= mapHeight) {
                y = mapHeight - viewPortHeight;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // No drawing
    }
}
