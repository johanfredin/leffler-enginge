package src.main.java.se.fredin.lefflerengine.object;

public abstract class MovingGameObject extends GameObject {

    public static final byte HEADING_NONE = 0;
    public static final byte HEADING_UP = 1;
    public static final byte HEADING_DOWN = 2;
    public static final byte HEADING_LEFT = 3;
    public static final byte HEADING_RIGHT = 4;

    public byte heading = HEADING_NONE;

    public float speed;

    public MovingGameObject(float x, float y, int w, int h, float speed) {
        super(x, y, w, h);
        this.speed = speed;
    }
}
