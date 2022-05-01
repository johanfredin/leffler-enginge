package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.Entity;

public abstract class GameObject implements Entity {

    public float x, y;
    public int w, h;
    public byte heading;
    public float speed;

    public GameObject(float x, float y, int w, int h, byte heading, float speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.heading = heading;
        this.speed = speed;
    }
}
