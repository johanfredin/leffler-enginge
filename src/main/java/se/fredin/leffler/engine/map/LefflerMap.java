package se.fredin.leffler.engine.map;

import se.fredin.leffler.engine.core.Rendereable;

public abstract class LefflerMap implements Rendereable {

    public final int w, h, scale;
    protected final String levelFileName;

    protected LefflerMap(int w, int h, int scale, String levelFileName) {
        this.w = w;
        this.h = h;
        this.scale = scale;
        this.levelFileName = levelFileName;
    }

}
