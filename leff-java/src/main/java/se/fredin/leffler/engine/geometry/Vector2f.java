package se.fredin.leffler.engine.geometry;


public class Vector2f extends Shape {

    public Vector2f() {
        super(0, 0);
    }

    public Vector2f(float x, float y) {
        super(x, y);
    }

    public Vector2f(Vector2f vector2f) {
        this(vector2f.x, vector2f.y);
    }

    public Vector2f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f add(Vector2f vector2f) {
        return add(vector2f.x, vector2f.y);
    }

    public Vector2f add(Vector2f v, Vector2f dest) {
        dest.x = x + v.x;
        dest.y = y + v.y;
        return dest;
    }

    public Vector2f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2f sub(Vector2f vector2f) {
        return sub(vector2f.x, vector2f.y);
    }

    public float dist (float x, float y) {
        final float x_d = x - this.x;
        final float y_d = y - this.y;
        return (float)Math.sqrt(x_d * x_d + y_d * y_d);
    }


    public Vector2f sub(Vector2f v, Vector2f dest) {
        dest.x = x - v.x;
        dest.y = y - v.y;
        return dest;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

