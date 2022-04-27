package se.fredin.lefflerengine.asset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpriteSheet {

    public final byte nColsX, nColsY, colW, colH;

    private final Map<Byte, BufferedImage[]> sheetMap;

    public SpriteSheet(byte nColsX, byte nColsY, byte colW, byte colH, String source, byte[] keys) {
        this.nColsX = nColsX;
        this.nColsY = nColsY;
        this.colW = colW;
        this.colH = colH;
        this.sheetMap = initSheetMap(source, keys);
    }

    public BufferedImage[] getSubSheet(byte key) {
        return sheetMap.get(key);
    }

    private Map<Byte, BufferedImage[]> initSheetMap(String source, final byte[] keys) {
        try {
            var sheetMap = new HashMap<Byte, BufferedImage[]>();
            var img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + source)));
            if (nColsX * colW > img.getWidth() || nColsY * colH > img.getHeight()) {
                throw new RuntimeException("Amount of cols and width and/or height exceeds image size");
            }

            for (byte y = 0; y < nColsY; y++) {
                var subImages = new BufferedImage[nColsX];
                for (byte x = 0; x < nColsX; x++) {
                    subImages[x] = img.getSubimage(x * colW, y * colH, colW, colW);
                }
                sheetMap.put(keys[y], subImages);
            }
            return sheetMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
