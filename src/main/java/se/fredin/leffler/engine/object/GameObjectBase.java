package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.core.GameEntity;

public abstract class GameObjectBase implements GameEntity {

    public float x, y;
    public int w, h;
    public float speed;

    public GameObjectBase(float x, float y, int w, int h, float speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
    }
}
