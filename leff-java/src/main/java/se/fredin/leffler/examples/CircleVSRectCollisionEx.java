package se.fredin.leffler.examples;

import se.fredin.leffler.engine.constants.Mode;
import se.fredin.leffler.engine.core.AABBGame;
import se.fredin.leffler.engine.core.CircleVSRectCollisionGame;
import se.fredin.leffler.engine.core.GameBase;
import se.fredin.leffler.engine.display.LefflerEngine;
import se.fredin.leffler.engine.io.Controller;

import java.awt.*;

public class CircleVSRectCollisionEx extends LefflerEngine {

    public CircleVSRectCollisionEx(int width, int height, Color bgColor, Mode mode) {
        super(width, height, bgColor, mode);
    }

    @Override
    public GameBase getGame() {
        return new CircleVSRectCollisionGame(controller);
    }

}