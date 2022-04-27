package se.fredin.lefflerengine;

import java.awt.*;

public interface Entity {

    void tick(float deltaTime);

    void draw(Graphics2D g2d);
}
