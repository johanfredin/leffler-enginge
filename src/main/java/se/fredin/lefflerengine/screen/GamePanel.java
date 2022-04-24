package se.fredin.lefflerengine.screen;

import se.fredin.lefflerengine.asset.SpriteSheet;
import se.fredin.lefflerengine.object.Player;
import src.main.java.se.fredin.lefflerengine.Controller;

import javax.swing.*;
import java.awt.*;

import static se.fredin.lefflerengine.object.MoveableGameObject.*;

public class GamePanel extends JPanel implements Runnable {

    static final int FPS = 60;
    static final int ONE_NANO = 1000000000;

    private static float deltaTime;

    final int originalTileSize;
    final int scale;
    final int nColsX;
    final int nColsY;

    public final int tileSize;
    final int screenWidth;
    final int screenHeight;

    Thread gameThread;
    boolean running;
    final Controller controller;

    // Default player pos/speed
    float playerX = 100;
    float playerY = 100;
    float playerVelocity = 4;

    Player player;

    public GamePanel(int originalTileSize, int scale, int nColsX, int nColsY, Color bgColor) {
        this.originalTileSize = originalTileSize;
        this.scale = scale;
        this.nColsX = nColsX;
        this.nColsY = nColsY;
        this.tileSize = originalTileSize * scale;
        this.screenWidth = nColsX * tileSize;
        this.screenHeight = nColsY * tileSize;
        this.controller = new Controller();
        this.player = new Player(
                playerX,
                playerY,
                tileSize,
                tileSize,
                playerVelocity,
                this,
                controller,
                new SpriteSheet(
                        (byte) 3,
                        (byte) 4,
                        (byte) originalTileSize,
                        (byte) originalTileSize,
                        "hero.png",
                        new byte[]{HEADING_DOWN, HEADING_LEFT, HEADING_RIGHT, HEADING_UP}
                ),
                10f);

        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setBackground(bgColor);
        super.setDoubleBuffered(true);
        super.addKeyListener(this.controller);
        super.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
        this.running = true;
    }

    @Override
    public void run() {
        float drawInterval = (float) ONE_NANO / FPS;
        long lastTime = System.nanoTime();
        long currTime;
        long elapsedTime;
        long timer = 0;
        int drawCount = 0;

        while (running) {
            currTime = System.nanoTime();

            elapsedTime = currTime - lastTime;

            deltaTime += elapsedTime / drawInterval;
            timer += elapsedTime;
            lastTime = currTime;

            if (deltaTime >= 1) {
                tick();
                repaint();  // Calls paintComponent when available
                deltaTime--;
                drawCount++;
            }

            if (timer >= ONE_NANO) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void tick() {
        player.tick(deltaTime);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        player.draw(g2d);
        g2d.dispose();
    }
}
