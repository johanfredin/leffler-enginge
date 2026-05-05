package se.fredin.leffler.engine.geometry;

public class Circle2f extends Shape {

    public float radius;

    public Circle2f(float x, float y, float radius) {
        super(x, y);
        this.radius = radius;
    }
}
