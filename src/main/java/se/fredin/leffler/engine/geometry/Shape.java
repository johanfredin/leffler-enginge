package se.fredin.leffler.engine.geometry;

public abstract class Shape {
    public float x, y;

    public Shape(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int intX() {
        return (int) x;
    }

    public int intY() {
        return (int) y;
    }
}
