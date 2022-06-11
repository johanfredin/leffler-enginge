package se.fredin.leffler.engine.geometry;

import se.fredin.leffler.engine.object.GameObjectBase;

public class Camera extends Shape {

    private final int mapWidth;
    private final int mapHeight;
    public int viewPortWidth, viewPortHeight;
    private final GameObjectBase objectOfInterest;

    public Camera(int viewPortWidth, int viewPortHeight, int mapWidth, int mapHeight, GameObjectBase objectOfInterest) {
        super(0, 0);
        this.viewPortWidth = viewPortWidth;
        this.viewPortHeight = viewPortHeight;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.objectOfInterest = objectOfInterest;
    }

    public void tick(float deltaTime) {
        // If nothing to focus on then don't do anything
        if (objectOfInterest == null) {
            return;
        }

        this.x = objectOfInterest.position.x - (viewPortWidth >> 1);
        this.y = objectOfInterest.position.y - (viewPortHeight >> 1);

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
