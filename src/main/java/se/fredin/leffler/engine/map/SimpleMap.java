package se.fredin.leffler.engine.map;

import java.awt.*;

public class SimpleMap extends LefflerMap {

    protected SimpleMap(int w, int h) {
        super(w, h, 1, null);
    }

    @Override
    public void tick(float deltaTime) {
        // Simply render
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect(0, 0, w, h);
        g2d.drawString("Base Map", 10, 10);
    }
}
