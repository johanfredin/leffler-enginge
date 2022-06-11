package se.fredin.leffler.engine.object;

import se.fredin.leffler.engine.asset.Animator;
import se.fredin.leffler.engine.asset.SpriteSheet;
import se.fredin.leffler.engine.constants.Heading;
import se.fredin.leffler.engine.geometry.Camera;
import se.fredin.leffler.engine.geometry.Vector2f;
import se.fredin.leffler.engine.io.Controller;

import java.awt.*;

public class Player extends GameObjectBase {

    private final Controller ctrl;
    private final Animator animator;

    public Vector2f oldPos;

    private Camera camera;

    public Player(Vector2f position, int w, int h, float speed, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame) {
        this(position, w, h, speed, ctrl, spriteSheet, ticksPerFrame, null);
    }

    public Player(Vector2f position, int w, int h, float speed, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame, Camera camera) {
        super(position, w, h, speed);
        this.ctrl = ctrl;
        this.animator = new Animator(spriteSheet, ticksPerFrame);
        this.camera = camera;
        this.heading = Heading.DOWN;
        this.oldPos = new Vector2f(position);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void tick(float deltaTime) {
        float currSpeedX = 0f, currSpeedY = 0f;
        this.oldPos.set(position.x, position.y);
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
            animator.tick();
        }
        position.add(currSpeedX, currSpeedY);
        bounds.adjustTo(position);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(animator.getCurrentFrame(heading), position.intX() - camera.intX(), position.intY() - camera.intY(), w, h, null);
        g2d.drawString("Player pos=" + position.toString(), 30, 30);
    }

    public void drawOldPos(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.drawRect(oldPos.intX(), oldPos.intY(), bounds.w, bounds.h);
    }
}
