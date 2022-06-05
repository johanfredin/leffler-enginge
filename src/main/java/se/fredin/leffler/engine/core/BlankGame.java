package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.display.StillCamera;
import se.fredin.leffler.engine.geometry.Camera;
import se.fredin.leffler.engine.geometry.Vector2f;
import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.object.Player;

import java.awt.*;


public class BlankGame implements GameBase {

    private final Controller controller;
    private final Player player;
    private final Camera camera = new StillCamera();

    public BlankGame(Controller controller) {
        this.controller = controller;
        this.player = getPlayer();
    }

    @Override
    public Player getPlayer() {
        return new Player(
                new Vector2f(24, 24),
                16,
                16,
                5.0f,
                this.controller,
                new SpriteSheet(
                        (byte) 3,
                        (byte) 4,
                        (byte) 16,
                        (byte) 16,
                        "hero.png",
                        new byte[]{Heading.DOWN, Heading.LEFT, Heading.RIGHT, Heading.UP}
                ),
                10f,
                camera);
    }

    @Override
    public void tick(float deltaTime) {
        player.tick(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        player.drawBounds(g2d, Color.RED);
    }
}
