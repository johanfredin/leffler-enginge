package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.display.StillCamera;
import se.fredin.leffler.engine.geometry.Rectangle2f;
import se.fredin.leffler.engine.geometry.Vector2f;
import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.object.Player;

import java.awt.*;
import java.util.List;


public class AABBGame implements GameBase {

    private final Controller controller;
    private final Player player;
    private final CollisionHandler ch;

    private final List<Rectangle2f> rects = List.of(
            new Rectangle2f(200, 200, 50 * SCALE, 50 * SCALE),
            new Rectangle2f(375, 300, 100 * SCALE, 1),
            new Rectangle2f(400, 100, 1, 60 * SCALE),
            new Rectangle2f(500, 500, SCALE, SCALE)
    );


    boolean collision;

    public AABBGame(Controller controller) {
        this.controller = controller;
        this.player = getPlayer();
        this.ch = new CollisionHandler();
    }

    @Override
    public Player getPlayer() {
        return new Player(
                new Vector2f(24, 24),
                16,
                16,
                8f,
                this.controller,
                new SpriteSheet(
                        (byte) 3,
                        (byte) 4,
                        (byte) 16,
                        (byte) 16,
                        "hero.png",
                        new byte[]{Heading.DOWN, Heading.LEFT, Heading.RIGHT, Heading.UP}
                ),
                6f,
                new StillCamera()
        );
    }

    @Override
    public void tick(float deltaTime) {
        player.tick(deltaTime);
        this.collision = checkCollision();
    }

    private boolean checkCollision() {
        var bounds = player.bounds;
        var collision = false;
        ch.handleCollision(player, rects);
        return collision;
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);
        g2d.setColor(collision ? Color.YELLOW : Color.BLUE);
        rects.forEach(
                rect -> g2d.drawRect(rect.intX(), rect.intY(), rect.w, rect.h)
        );
    }
}
