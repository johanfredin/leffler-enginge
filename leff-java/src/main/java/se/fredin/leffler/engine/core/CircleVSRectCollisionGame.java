package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.geometry.Rectangle2f;
import se.fredin.leffler.engine.geometry.Vector2f;
import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.object.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleVSRectCollisionGame implements GameBase {

    public Controller controller;
    public Player player;
    public Rectangle2f rect = new Rectangle2f(400, 300, 100, 100);
    public List<Vector2f> radius = new ArrayList<>(List.of(new Vector2f(0,0), new Vector2f(0,0)));

    public CircleVSRectCollisionGame(Controller controller) {
        this.controller = controller;
        this.player = getPlayer();
    }

    @Override
    public Player getPlayer() {
        return new Player(
                new Vector2f(50f, 50f),
                64, 64, 2,
                controller
        );
    }

    @Override
    public void tick(float deltaTime) {
        player.tick(deltaTime);
        radius.get(0).set(player.position.x + (player.w /2f), player.position.y + (player.h / 2f));
        radius.get(1).set(player.position.x + player.w, player.position.y + (player.h / 2f));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.gray);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(player.position.intX(), player.position.intY(), player.w, player.h);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(radius.get(0).intX(), radius.get(0).intY(), radius.get(1).intX(), radius.get(1).intY());
        g2d.setColor(Color.RED);
        g2d.fillRect(rect.intX(), rect.intY(), rect.w, rect.h);
        player.draw(g2d);
    }
}
