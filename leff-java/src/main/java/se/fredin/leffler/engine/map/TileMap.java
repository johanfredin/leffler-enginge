package se.fredin.leffler.engine.map;

import se.fredin.leffler.engine.core.CollisionHandler;
import se.fredin.leffler.engine.geometry.Camera;
import se.fredin.leffler.engine.geometry.Rectangle2f;
import se.fredin.leffler.engine.object.Player;
import se.fredin.leffler.engine.util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TileMap extends LefflerMap {

    private int[][] tilesIndex;
    private BufferedImage[] tileImages;
    private Camera camera;
    private final List<Rectangle2f> bounds;
    private final int nCols, nRows;
    private final int scaledTileSize;
    private final CollisionHandler ch;

    public TileMap(String levelFileName, Map<Integer, String> tileset, int nCols, int nRows, byte scale, byte tileSize) {
        super((tileSize * nCols) * scale, (tileSize * nRows) * scale, scale, levelFileName);
        this.scaledTileSize = tileSize * scale;
        this.nCols = nCols;
        this.nRows = nRows;
        this.bounds = getBounds();
        initTiles(levelFileName, tileset);
        this.ch = new CollisionHandler();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    private void initTiles(String levelFileName, Map<Integer, String> tileset) {
        // Load img dict
        tileImages = new BufferedImage[tileset.size()];
        tileset.forEach((idx, imgName) -> tileImages[idx] = Utils.readImg("/tiles/" + imgName));

        // Parse map file
        tilesIndex = new int[nRows][nCols];
        try (var sc = new Scanner(Utils.readFile("/maps/" + levelFileName))) {
            int y = 0;
            while (sc.hasNextLine()) {
                var row = sc.nextLine().split("\t");
                if (row.length != nCols) {
                    throw new RuntimeException("Amount of cols should be=" + nCols + ". Was=" + row.length);
                }

                for (int x = 0; x < row.length; x++) {
                    tilesIndex[y][x] = Integer.parseInt(row[x]);
                }
                y++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    boolean collision;

    public void tick(float deltaTime, Player player) {
        player.tick(deltaTime);
        ch.handleCollision(player, bounds);
    }

    public void draw(Graphics2D g2d) {
        for (int y = 0; y < nRows; y++) {
            for (int x = 0; x < nCols; x++) {
                int camX = (int) ((x * scaledTileSize) - camera.x);
                int camY = (int) ((y * scaledTileSize) - camera.y);
                g2d.drawImage(tileImages[tilesIndex[y][x]], camX, camY, scaledTileSize, scaledTileSize, null);
            }
        }
        g2d.setColor(collision ? Color.WHITE : Color.RED);
    }

    @Override
    protected List<Rectangle2f> getBounds() {
        return List.of(
                new Rectangle2f(4 * scaledTileSize, 4 * scaledTileSize, 80 * scale, 48 * scale),
                new Rectangle2f(20 * scaledTileSize, 4 * scaledTileSize, 80 * scale, 48 * scale)
        );
    }
}
