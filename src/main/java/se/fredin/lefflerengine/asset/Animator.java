package se.fredin.lefflerengine.asset;

import java.awt.image.BufferedImage;

public class Animator {

    private final SpriteSheet spriteSheet;
    private final float ticksPerFrame;
    private int counter;

    private byte currCol = 0;

    public Animator(SpriteSheet spriteSheet, float ticksPerFrame) {
        this.spriteSheet = spriteSheet;
        this.ticksPerFrame = ticksPerFrame;
    }

    public void tick() {
        counter++;
        if (counter >= ticksPerFrame) {
            // Time to update
            if (currCol < spriteSheet.nColsX - 1) {
                currCol++;
            } else {
                currCol = 0;
            }
            counter = 0;
        }
    }

    public BufferedImage getCurrentFrame(byte key) {
        return spriteSheet.getSubSheet(key)[currCol];
    }
}
