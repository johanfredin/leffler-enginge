package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.asset.Animator;
import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.display.Camera;

import java.awt.*;

public class Player extends PlayerBase {

    private final Controller ctrl;
    private Camera camera;
    private byte heading = Heading.UP;

    final float ticksPerFrame;
    final Animator animator;

    public Player(float x, float y, int w, int h, float speed, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame) {
        super(x, y, w, h, speed, ctrl, Color.RED);
        this.ctrl = ctrl;
        this.ticksPerFrame = ticksPerFrame;
        this.animator = new Animator(spriteSheet, ticksPerFrame);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void tick(float deltaTime) {
        var moving = ctrl.up | ctrl.down | ctrl.left | ctrl.right;
        if (moving) {
            if (ctrl.up) {
                heading = Heading.UP;
                y -= speed;
            }
            if (ctrl.down) {
                heading = Heading.DOWN;
                y += speed;
            }
            if (ctrl.left) {
                heading = Heading.LEFT;
                x -= speed;
            }
            if (ctrl.right) {
                heading = Heading.RIGHT;
                x += speed;
            }
            animator.tick();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(animator.getCurrentFrame(heading), (int)(x - camera.x), (int)(y - camera.y), w, h, null);
    }

}
