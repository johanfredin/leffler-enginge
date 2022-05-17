package se.fredin.leffler.engine.geometry;


public class Rectangle2f extends Shape {

    public int w, h;
    public final int clampX, clampY;


    public Rectangle2f(float x, float y, int w, int h) {
        this(x, y, w, h, 0, 0);
    }

    public Rectangle2f(float x, float y, int w, int h, int clampX, int clampY) {
        super(x, y);
        this.w = w;
        this.h = h;
        this.clampX = clampX;
        this.clampY = clampY;
    }

    public void move(Vector2f pos) {
        this.x = pos.x + clampX;
        this.y = pos.y + clampY;
    }
}
