package se.fredin.lefflerengine.map;

import se.fredin.lefflerengine.Entity;
import se.fredin.lefflerengine.screen.GamePanel;
import se.fredin.lefflerengine.util.LefflerUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TileMap implements Entity {

    private BufferedImage[][] tiles;
    private final GamePanel gp;

    public TileMap(String levelFileName, Map<Integer, String> tileset, GamePanel gp) {
        this.gp = gp;
        initTiles(levelFileName, tileset);
    }

    private void initTiles(String levelFileName, Map<Integer, String> tileset) {
        // Load img dict
        Map<Integer, BufferedImage> tilesDict = new HashMap<>();
        tileset.forEach((idx, imgName) -> tilesDict.put(idx, LefflerUtils.readImg("/tiles/" + imgName)));

        // Parse map file
        tiles = new BufferedImage[12][16];
        try (var sc = new Scanner(LefflerUtils.readFile("/maps/" + levelFileName))) {
            int y = 0;
            while (sc.hasNextLine()) {
                var row = sc.nextLine().split("\t");
                for (int x = 0; x < row.length; x++) {
                    tiles[y][x] = tilesDict.get(Integer.parseInt(row[x]));
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
        for (int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[y].length; x++) {
                g2d.drawImage(tiles[y][x], gp.tileSize * x, gp.tileSize * y, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
