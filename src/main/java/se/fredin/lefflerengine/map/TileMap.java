package se.fredin.lefflerengine.map;

import se.fredin.lefflerengine.Entity;
import se.fredin.lefflerengine.screen.GamePanel;
import se.fredin.lefflerengine.util.LefflerUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class TileMap implements Entity {

    private int[][] tilesIndex;
    private BufferedImage[] tileImages;
    private final GamePanel gp;

    private final int nCols, nRows;

    public TileMap(String levelFileName, Map<Integer, String> tileset, GamePanel gp, int nCols, int nRows) {
        this.gp = gp;
        this.nCols = nCols;
        this.nRows = nRows;
        initTiles(levelFileName, tileset);
    }

    private void initTiles(String levelFileName, Map<Integer, String> tileset) {
        // Load img dict
        tileImages = new BufferedImage[tileset.size()];
        tileset.forEach((idx, imgName) -> tileImages[idx] = LefflerUtils.readImg("/tiles/" + imgName));

        // Parse map file
        tilesIndex = new int[nRows][nCols];
        try (var sc = new Scanner(LefflerUtils.readFile("/maps/" + levelFileName))) {
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
        for (int y = 0; y < nRows; y++) {
            for(int x = 0; x < nCols; x++) {
                g2d.drawImage(tileImages[tilesIndex[y][x]], gp.tileSize * x, gp.tileSize * y, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
