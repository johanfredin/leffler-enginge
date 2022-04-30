package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.Entity;

public abstract class GameObject implements Entity {

    public float worldX;
    public float worldY;
    public int w, h;

    public byte heading;

    public float speed;

    public GameObject(float worldX, float worldY, int w, int h, byte heading, float speed) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.w = w;
        this.h = h;
        this.heading = heading;
        this.speed = speed;
    }
}
