package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.Entity;
import se.fredin.lefflerengine.constants.Heading;

import java.awt.*;

public abstract class GameObject implements Entity {

    public float x;
    public float y;
    public int w, h;

    public byte heading = Heading.NONE;

    public float speed;

    public GameObject(float x, float y, int w, int h, byte heading, float speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.heading = heading;
        this.speed = speed;
    }
}
