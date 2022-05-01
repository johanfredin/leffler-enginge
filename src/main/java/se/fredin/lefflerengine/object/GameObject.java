package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.GameEntity;

public abstract class GameObject implements GameEntity {

    public float x, y;
    public int w, h;
    public float speed;

    public GameObject(float x, float y, int w, int h, float speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
    }
}
