package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.core.GameBase;
import se.fredin.leffler.engine.core.Rendereable;
import se.fredin.leffler.engine.geometry.Rectangle2f;
import se.fredin.leffler.engine.geometry.Vector2f;

import java.awt.*;


public abstract class GameObjectBase implements Rendereable {

    public Vector2f position;
    public int w, h;
    public float speed;
    public byte heading = Heading.NONE;

    public final Rectangle2f bounds;

    public GameObjectBase(Vector2f position, int w, int h, float speed) {
        this.position = position;
        this.w = w * GameBase.SCALE;
        this.h = h * GameBase.SCALE;
        this.speed = speed;
        this.bounds = new Rectangle2f(
                position.x,
                position.y,
                w * GameBase.SCALE,
                h * GameBase.SCALE
        );
    }

    public void drawBounds(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.drawRect(bounds.intX(), bounds.intY(), bounds.w, bounds.h);
    }

}
