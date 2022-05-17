package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.display.StillCamera;
import se.fredin.leffler.engine.geometry.Vector2f;
import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.object.Block;
import se.fredin.leffler.engine.object.Player;

import java.awt.*;

public class BlankGame implements GameBase {

    private final Controller controller;
    private final Player player;
    private final Block block;

    public BlankGame(Controller controller) {
        this.controller = controller;
        this.player = getPlayer();
        this.block = new Block(100, 100, 50, 50, 0);
    }

    @Override
    public Player getPlayer() {
        var p = new Player(
                new Vector2f(24, 24),
                16,
                16,
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
        p.setCamera(new StillCamera());
        return p;
    }

    @Override
    public void tick(float deltaTime) {
        player.tick(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);
        player.drawBounds(g2d, Color.RED);
        block.drawBounds(g2d, Color.BLUE);
        g2d.drawString(player.position.toString() + "\nblock=" + block.position.toString(), 50, 30);
    }
}
