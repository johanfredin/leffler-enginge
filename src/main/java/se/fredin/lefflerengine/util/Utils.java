package se.fredin.lefflerengine.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Utils {

    public static BufferedImage readImg(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(Utils.class.getResourceAsStream(path)));
        } catch (IOException e) {
            throw new RuntimeException("Could not read image at path=" + path);
        }
    }

    public static Path readFile(String filePath) {
        try {
            return Paths.get(Objects.requireNonNull(Utils.class.getResource(filePath)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Could not read file at path=" + filePath);
        }
    }
}
