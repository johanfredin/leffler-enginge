package se.fredin.leffler.engine.map;

import se.fredin.leffler.engine.geometry.Rectangle2f;

import java.util.List;

public abstract class LefflerMap {

    public final int w, h, scale;
    protected final String levelFileName;

    protected LefflerMap(int w, int h, int scale, String levelFileName) {
        this.w = w;
        this.h = h;
        this.scale = scale;
        this.levelFileName = levelFileName;
    }

    protected abstract List<Rectangle2f> getBounds();

}
