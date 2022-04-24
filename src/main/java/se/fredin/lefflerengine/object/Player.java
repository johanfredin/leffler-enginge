package src.main.java.se.fredin.lefflerengine.object;

import src.main.java.se.fredin.lefflerengine.Controller;
import src.main.java.se.fredin.lefflerengine.screen.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovingGameObject {

    GamePanel gp;
    Controller ctrl;

    private BufferedImage spriteSheet;
    private int u, v;  // x and y offsets within sprite sheet


    public Player(float x, float y, int w, int h, float speed, GamePanel gp, Controller ctrl) {
        super(x, y, w, h, speed);
        this.gp = gp;
        this.ctrl = ctrl;
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
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, (int) y, gp.tileSize, gp.tileSize);
    }

    private BufferedImage initSpriteSheet(String filePath) {
        return null;
    }
}
