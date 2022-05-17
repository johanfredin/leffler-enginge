package se.fredin.leffler.engine.geometry;


public class Vector2f extends Shape {

    public Vector2f(float x, float y) {
        super(x, y);
    }

    public Vector2f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2f add(Vector2f vector2f) {
        return add(vector2f.x, vector2f.y);
    }

    public Vector2f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2f sub(Vector2f vector2f) {
        return sub(vector2f.x, vector2f.y);
    }

    public Vector2f div(Vector2f vector2f) {
        return div(vector2f.x, vector2f.y);
    }

    private Vector2f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

