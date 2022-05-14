package se.fredin.leffler.engine.core;

import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.object.PlayerBase;

import java.awt.*;

public class BlankGame implements GameBase {

    private final Controller controller;

    private final PlayerBase player;

    public BlankGame(Controller controller) {
        this.controller = controller;
        this.player = getPlayer();
    }

    @Override
    public PlayerBase getPlayer() {
        return new PlayerBase(
                24,
                24,
                16 * SCALE,
                16 * SCALE,
                6f,
                this.controller, Color.RED);
    }

    @Override
    public void tick(float deltaTime) {
        player.tick(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.draw(g2d);
    }
}
