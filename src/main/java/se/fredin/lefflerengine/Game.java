package se.fredin.lefflerengine;

import se.fredin.lefflerengine.asset.SpriteSheet;
import se.fredin.lefflerengine.constants.Heading;
import se.fredin.lefflerengine.constants.Mode;
import se.fredin.lefflerengine.display.Camera;
import se.fredin.lefflerengine.map.TileMap;
import se.fredin.lefflerengine.object.Player;

import java.awt.*;
import java.util.Map;

public class Game implements GameEntity {

    public static final byte SCALE = 3;

    final Controller controller;
    Camera camera;
    final Leffler leffler;

    final Mode mode;

    public final Player player;
//    public final Camera camera;
    TileMap tileMap;

    private Canvas canvas;

    public Game(Leffler leffler, Mode mode) {
        this.mode = mode;
        this.leffler = leffler;
        this.controller = new Controller();
        this.tileMap = new TileMap(
                "world_01.txt",
                Map.of(
                        0, "grass.png",
                        1, "tree.png",
                        2, "water.png",
                        3, "dirt.png"
                ),
                this,
                32,
                24,
                SCALE,
                (byte) 16
        );
        this.player = new Player(
                24,
                24,
                16 * SCALE,
                16 * SCALE,
                6f,
                camera,
                controller,
                new SpriteSheet(
                        (byte) 3,
                        (byte) 4,
                        (byte) 16,
                        (byte) 16,
                        "hero.png",
                        new byte[]{Heading.DOWN, Heading.LEFT, Heading.RIGHT, Heading.UP}
                ),
                10f);

        camera = new Camera(leffler.width, leffler.height, player);
    }

    @Override
    public void tick(float deltaTime) {

    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
