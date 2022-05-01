package se.fredin.lefflerengine.map;

import se.fredin.lefflerengine.Game;
import se.fredin.lefflerengine.util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class TileMap extends LefflerMap {

    private int[][] tilesIndex;
    private BufferedImage[] tileImages;
    private final Game game;

    private final int nCols, nRows;

    private final byte originalTileSize;
    private final int scaledTileSize;

    public TileMap(String levelFileName, Map<Integer, String> tileset, Game game, int nCols, int nRows, byte scale, byte tileSize) {
        super((tileSize * nCols) * scale, (tileSize * nRows) * scale, scale, levelFileName);
        this.originalTileSize = tileSize;
        this.scaledTileSize = tileSize * scale;
        this.game = game;
        this.nCols = nCols;
        this.nRows = nRows;
        initTiles(levelFileName, tileset);
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

    @Override
    public void tick(float deltaTime) {
        // Not needed here
    }

    @Override
    public void draw(Graphics2D g2d) {
//        for (int y = 0; y < nRows; y++) {
//            for (int x = 0; x < nCols; x++) {
//                int camX = (int) ((x * scaledTileSize) - gp.camera.x);
//                int camY = (int) ((y * scaledTileSize) - gp.camera.y);
//                g2d.drawImage(tileImages[tilesIndex[y][x]], camX, camY, scaledTileSize, scaledTileSize, null);
//            }
//        }
    }

}
