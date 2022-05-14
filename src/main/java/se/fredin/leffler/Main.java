package se.fredin.leffler;

import se.fredin.leffler.engine.constants.Mode;
import se.fredin.leffler.examples.TileMapGameEx;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new TileMapGameEx(800, 600, Color.BLACK, Mode.DEBUG).start();
//        new AABBEx(800, 600, Color.BLACK, Mode.DEBUG).start();
    }

}
