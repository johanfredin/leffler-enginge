package se.fredin.lefflerengine;

import java.awt.*;
import java.io.Serializable;

public interface GameEntity extends Serializable {

    void tick(float deltaTime);

    void draw(Graphics2D g2d);
}
