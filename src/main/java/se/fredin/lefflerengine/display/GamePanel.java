package se.fredin.lefflerengine.display;

import se.fredin.lefflerengine.asset.SpriteSheet;
import se.fredin.lefflerengine.constants.Heading;
import se.fredin.lefflerengine.map.TileMap;
import se.fredin.lefflerengine.object.Player;
import src.main.java.se.fredin.lefflerengine.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GamePanel extends JPanel implements Runnable {

    static final int FPS = 60;
    static final int ONE_NANO = 1000000000;

    public final int tileSize;
    public final int screenWidth;
    public final int screenHeight;

    private static float deltaTime;

    final int originalTileSize;
    final int scale;
    final int nColsX;
    final int nColsY;

    Thread gameThread;
    boolean running;
    final Controller controller;

    public final Player player;
    public final Camera camera;
    TileMap tileMap;

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
                24,
                24,
                tileSize,
                tileSize,
                8f,
                this,
                controller,
                new SpriteSheet(
                        (byte) 3,
                        (byte) 4,
                        (byte) originalTileSize,
                        (byte) originalTileSize,
                        "hero.png",
                        new byte[]{Heading.DOWN, Heading.LEFT, Heading.RIGHT, Heading.UP}
                ),
                10f);

        this.tileMap = new TileMap(
                "world_01.txt",
                Map.of(
                        0, "grass.png",
                        1, "tree.png",
                        2, "water.png",
                        3, "dirt.png"
                ),
                this,
                32,
                24
        );

        this.camera = new Camera(this, player);
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
        camera.tick(deltaTime);
        player.tick(deltaTime);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        tileMap.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
