package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.asset.Animator;
import se.fredin.lefflerengine.asset.SpriteSheet;
import se.fredin.lefflerengine.screen.GamePanel;
import src.main.java.se.fredin.lefflerengine.Controller;

import java.awt.*;

public class Player extends MoveableGameObject {

    GamePanel gp;
    Controller ctrl;

    final SpriteSheet spriteSheet;
    final float ticksPerFrame;

    final Animator animator;

    public Player(float x, float y, int w, int h, float speed, GamePanel gp, Controller ctrl, SpriteSheet spriteSheet, float ticksPerFrame) {
        super(x, y, w, h, speed);
        this.gp = gp;
        this.ctrl = ctrl;
        this.spriteSheet = spriteSheet;
        this.ticksPerFrame = ticksPerFrame;
        this.animator = new Animator(spriteSheet, ticksPerFrame);
        this.heading = HEADING_DOWN;
    }

    @Override
    public void tick(float deltaTime) {
        if (ctrl.up) {
            heading = HEADING_UP;
            y -= speed;
        }
        if (ctrl.down) {
            heading = HEADING_DOWN;
            y += speed;
        }
        if (ctrl.left) {
            heading = HEADING_LEFT;
            x -= speed;
        }
        if (ctrl.right) {
            heading = HEADING_RIGHT;
            x += speed;
        }

        if (ctrl.up | ctrl.down | ctrl.left | ctrl.right) {
            animator.tick();
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(animator.getCurrentFrame(heading), (int) x, (int) y, w, h, null);
    }

}
