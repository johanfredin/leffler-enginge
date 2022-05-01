package se.fredin.lefflerengine;

import se.fredin.lefflerengine.constants.Mode;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new Leffler(800, 600, Color.BLACK, Mode.DEBUG).start();
    }

}
