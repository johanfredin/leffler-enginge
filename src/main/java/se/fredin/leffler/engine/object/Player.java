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
    private final Animator animator;

    private Camera camera;

    public Player(Vector2f position, int w, int h, float speed, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame) {
        this(position, w, h, speed, ctrl, spriteSheet, ticksPerFrame, null);
    }

    public Player(Vector2f position, int w, int h, float speed, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame, Camera camera) {
        super(position, w, h, speed, 0, 0);
        this.ctrl = ctrl;
        this.animator = new Animator(spriteSheet, ticksPerFrame);
        this.camera = camera;
        this.heading = Heading.DOWN;
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
            bounds.tick(position);
            animator.tick();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(animator.getCurrentFrame(heading), position.intX() - camera.intX(), position.intY() - camera.intY(), w, h, null);
    }

}
