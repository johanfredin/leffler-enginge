package se.fredin.leffler.engine.display;

import se.fredin.leffler.engine.constants.Mode;
import se.fredin.leffler.engine.core.GameBase;
import se.fredin.leffler.engine.io.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Main game loop
 */
public abstract class LefflerEngine extends JFrame implements Runnable {

    // Render loop props
    public static final float DESIRED_FPS = 1.0f / 60f;
    private static float deltaTime;
    private long nextStatTime;
    private int ups;
    private int fps;

    public final int width;
    public final int height;
    public final Color bgColor;

    private final GameBase game;
    private final Canvas canvas;

    protected final Mode mode;
    protected final Controller controller;

    private boolean running;

    public LefflerEngine(int width, int height, Color bgColor, Mode mode) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.mode = mode;
        this.controller = getController();

        super.setTitle(getGameTitle());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        super.add(canvas);
        super.pack();

        canvas.createBufferStrategy(getNumBuffers());

        super.setLocationRelativeTo(null);
        super.setVisible(true);

        super.addKeyListener(this.controller);
        this.game = getGame();
    }

    public String getGameTitle() {
        return this.getClass().getSimpleName();
    }

    public abstract GameBase getGame();

    public abstract Controller getController();

    public abstract int getNumBuffers();

    public void start() {
        Thread gameThread = new Thread(this);
        gameThread.start();
        this.running = true;
    }

    @Override
    public void run() {
        float accumulator = 0f;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            deltaTime = (currentTime - lastUpdate) / 1000f;
            accumulator += deltaTime;
            lastUpdate = currentTime;

            if (accumulator >= DESIRED_FPS) {
                while (accumulator >= DESIRED_FPS) {
                    tick();
                    ups++;
                    accumulator -= DESIRED_FPS;
                }
                draw();
                fps++;
            }
            checkStats();
        }
    }

    public void tick() {
        game.tick(deltaTime);
    }

    public void draw() {
        var bufferStrategy = canvas.getBufferStrategy();
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, width, height);

        // Draw game content
        game.draw(g2d);

        g2d.dispose();
        bufferStrategy.show();
    }

    private void checkStats() {
        if (mode == Mode.DEBUG) {
            if (System.currentTimeMillis() > nextStatTime) {
                System.out.printf("FPS: %d, UPS: %d\n", fps, ups);
                fps = ups = 0;
                this.nextStatTime = System.currentTimeMillis() + 1000;
            }
        }
    }
}
