package se.fredin.leffler.engine.geometry;


public class Rectangle2f extends Shape {

    public int w, h;
    public final int clampX, clampY;

    public Rectangle2f(float x, float y, int w, int h, int clampX, int clampY) {
        super(x, y);
        this.w = w - clampX;
        this.h = h - clampY;
        this.clampX = clampX;
        this.clampY = clampY;
    }

    public void tick(Vector2f pos) {
        this.x = pos.x + clampX;
        this.y = pos.y + clampY;
    }

    public boolean overlaps (Rectangle2f r) {
        return x < r.x + r.w && x + w > r.x && y < r.y + r.h && y + h > r.y;
    }

}
