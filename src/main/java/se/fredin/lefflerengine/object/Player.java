package se.fredin.lefflerengine.object;

import se.fredin.lefflerengine.screen.GamePanel;
import src.main.java.se.fredin.lefflerengine.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends MoveableGameObject {

    GamePanel gp;
    Controller ctrl;

    private BufferedImage spriteSheet;
    private int u, v;  // x and y offsets within sprite sheet


    public Player(float x, float y, int w, int h, float speed, GamePanel gp, Controller ctrl) {
        super(x, y, w, h, speed);
        this.gp = gp;
        this.ctrl = ctrl;
        try {
            this.spriteSheet = initSpriteSheet("hero.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private BufferedImage initSpriteSheet(String filePath) throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + filePath)));
    }
}
