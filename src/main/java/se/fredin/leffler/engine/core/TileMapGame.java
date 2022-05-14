package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.display.Camera;
import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.map.TileMap;
import se.fredin.leffler.engine.object.Player;

import java.awt.*;

public class TileMapGame implements GameBase {

    private final Controller controller;
    private final Camera camera;

    private final Player player;
    private final TileMap tileMap;

    public TileMapGame(int width, int height, TileMap tileMap, Controller controller) {
        this.controller = controller;
        this.tileMap = tileMap;
        this.player = getPlayer();

        this.camera = new Camera(width, height, tileMap.w, tileMap.h, player);
        this.tileMap.setCamera(camera);
        this.player.setCamera(camera);
    }

    @Override
    public Player getPlayer() {
        return new Player(
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
