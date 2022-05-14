package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.io.Controller;

import java.awt.*;

public class PlayerBase extends GameObjectBase {

    private final Controller ctrl;
    private final Color color;

    public PlayerBase(float x, float y, int w, int h, float speed, Controller ctrl, Color color) {
        super(x, y, w, h, speed);
        this.color = color;
        this.ctrl = ctrl;
    }

    @Override
    public void tick(float deltaTime) {
        var moving = ctrl.up | ctrl.down | ctrl.left | ctrl.right;
        if (moving) {
            if (ctrl.up) {
                y -= speed;
            }
            if (ctrl.down) {
                y += speed;
            }
            if (ctrl.left) {
                x -= speed;
            }
            if (ctrl.right) {
                x += speed;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect((int)x, (int) y, w, h);
    }

}
