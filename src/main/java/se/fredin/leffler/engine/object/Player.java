package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.geometry.Vector2f;
import se.fredin.leffler.engine.io.Controller;
import se.fredin.leffler.engine.asset.Animator;
import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.geometry.Camera;

import java.awt.*;

public class Player extends GameObjectBase {

    private final Controller ctrl;
    private Camera camera;
    private byte heading = Heading.UP;

    final float ticksPerFrame;
    final Animator animator;

    public Player(Vector2f position, int w, int h, float speed, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame) {
        super(position, w, h, speed, 2, 2);
        this.ctrl = ctrl;
        this.ticksPerFrame = ticksPerFrame;
        this.animator = new Animator(spriteSheet, ticksPerFrame);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void tick(float deltaTime) {
        float currSpeedX = 0f, currSpeedY = 0f;
        var moving = ctrl.up | ctrl.down | ctrl.left | ctrl.right;
        if (moving) {
            if (ctrl.up) {
                heading = Heading.UP;
                currSpeedY = -speed;
            }
            if (ctrl.down) {
                heading = Heading.DOWN;
                currSpeedY = speed;
            }
            if (ctrl.left) {
                heading = Heading.LEFT;
                currSpeedX = -speed;
            }
            if (ctrl.right) {
                heading = Heading.RIGHT;
                currSpeedX = speed;
            }
            position.add(currSpeedX, currSpeedY);
            bounds.move(position);
            animator.tick();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(animator.getCurrentFrame(heading), position.intX() - camera.intX(), position.intY() - camera.intY(), w, h, null);
    }

}
