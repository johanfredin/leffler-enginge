package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.core.GameBase;
import se.fredin.leffler.engine.core.Rendereable;

import java.awt.*;

public abstract class GameObjectBase implements Rendereable {

    public float x, y;
    public int w, h;
    public float speed;
    public byte heading = Heading.NONE;

    public final Rectangle bounds;

    public GameObjectBase(float x, float y, int w, int h, float speed) {
        this(x, y, w, h, speed, 0, 0);
    }

    public GameObjectBase(float x, float y, int w, int h, float speed, int clampX, int clampY) {
        this.x = x;
        this.y = y;
        this.w = w * GameBase.SCALE;
        this.h = h * GameBase.SCALE;
        this.speed = speed;
        this.bounds = new Rectangle((int) (x + clampX), (int) (y + clampY), (w - clampX) * GameBase.SCALE, (h - clampY) * GameBase.SCALE);
    }

    public void drawBounds(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
