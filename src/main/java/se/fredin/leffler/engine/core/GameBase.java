package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.object.PlayerBase;

import java.awt.*;
import java.io.Serializable;

public interface GameBase extends Serializable {

    byte SCALE = 3;

    PlayerBase getPlayer();

    void tick(float deltaTime);

    void draw(Graphics2D g2d);

}
