package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.GameEntity;
import se.fredin.lefflerengine.display.Camera;

public abstract class GameObject implements GameEntity {

    public float x, y;
    public int w, h;
    public byte heading;
    public float speed;
    public final Camera camera;

    public GameObject(float x, float y, int w, int h, byte heading, float speed, Camera camera) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.heading = heading;
        this.speed = speed;
        this.camera = camera;
    }
}
