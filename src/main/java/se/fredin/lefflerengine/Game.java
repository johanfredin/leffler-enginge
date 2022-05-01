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
    TileMap tileMap;

    public Game(Leffler leffler, Controller controller, Mode mode) {
        this.mode = mode;
        this.leffler = leffler;
        this.controller = controller;
        this.tileMap = new TileMap(
                "world_01.txt",
                Map.of(
                        0, "grass.png",
                        1, "tree.png",
                        2, "water.png",
                        3, "dirt.png"
                ),
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
                this.controller,
                new SpriteSheet(
                        (byte) 3,
                        (byte) 4,
                        (byte) 16,
                        (byte) 16,
                        "hero.png",
                        new byte[]{Heading.DOWN, Heading.LEFT, Heading.RIGHT, Heading.UP}
                ),
                10f);

        this.camera = new Camera(leffler.width, leffler.height, tileMap.w, tileMap.h, player);
        this.tileMap.setCamera(camera);
        this.player.setCamera(camera);
    }

    @Override
    public void tick(float deltaTime) {
        camera.tick(deltaTime);
        player.tick(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        tileMap.draw(g2d);
        player.draw(g2d);
    }
}
