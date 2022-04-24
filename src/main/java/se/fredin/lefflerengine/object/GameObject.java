package src.main.java.se.fredin.lefflerengine.object;

import java.awt.*;

public abstract class GameObject {

    public float x, y, speed;
    public int w, h;

    public GameObject(float x, float y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void tick(float deltaTime);

    public abstract void draw(Graphics2D g2d);
}
