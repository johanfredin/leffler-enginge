package se.fredin.leffler.examples;

import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.core.TileMapGame;
import se.fredin.leffler.engine.display.LefflerEngine;
import se.fredin.leffler.engine.constants.Mode;
import se.fredin.leffler.engine.map.TileMap;

import java.awt.*;
import java.util.Map;

public class TileMapGameEx extends LefflerEngine {

    public TileMapGameEx(int width, int height, Color bgColor, Mode mode) {
        super(width, height, bgColor, mode);
    }

    @Override
    public String getGameTitle() {
        return this.getClass().getSimpleName();
    }

    @Override
    public TileMapGame getGame() {
        return new TileMapGame(width, height, new TileMap(
                "world_01.txt",
                Map.of(
                        0, "grass.png",
                        1, "tree.png",
                        2, "water.png",
                        3, "dirt.png"
                ),
                32,
                24,
                TileMapGame.SCALE,
                (byte) 16
        ) ,controller);
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
