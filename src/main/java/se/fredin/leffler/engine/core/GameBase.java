package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.object.Player;

import java.awt.*;
import java.io.Serializable;

public interface GameBase extends Serializable {

    byte SCALE = 3;

    Player getPlayer();

    void tick(float deltaTime);

    void draw(Graphics2D g2d);

}
