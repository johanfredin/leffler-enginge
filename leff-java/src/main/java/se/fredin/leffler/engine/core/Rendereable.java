package se.fredin.leffler.engine.core;

import java.awt.*;

public interface Rendereable {

    void tick(float deltaTime);

    void draw(Graphics2D g2d);

}
