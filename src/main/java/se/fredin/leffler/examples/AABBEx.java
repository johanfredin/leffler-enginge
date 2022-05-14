package se.fredin.leffler.examples;

import se.fredin.leffler.engine.constants.Mode;
import se.fredin.leffler.engine.core.BlankGame;
import se.fredin.leffler.engine.core.GameBase;
import se.fredin.leffler.engine.display.LefflerEngine;
import se.fredin.leffler.engine.io.Controller;

import java.awt.*;

public class AABBEx extends LefflerEngine {

    public AABBEx(int width, int height, Color bgColor, Mode mode) {
        super(width, height, bgColor, mode);
    }

    @Override
    public GameBase getGame() {
        return new BlankGame(controller);
    }

    @Override
    public Controller getController() {
        return new Controller();
    }

    @Override
    public int getNumBuffers() {
        return 2;
    }
}
