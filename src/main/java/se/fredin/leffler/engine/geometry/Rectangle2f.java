package se.fredin.leffler.engine.geometry;


public class Rectangle2f extends Shape {

    public int w, h;

    public Rectangle2f(float x, float y, int w, int h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }

    public void adjustTo(Vector2f pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public boolean overlaps (Rectangle2f r) {
        return x < r.x + r.w & x + w > r.x & y < r.y + r.h & y + h > r.y;
    }

}
