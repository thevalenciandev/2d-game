package com.thevalenciandev.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private final String path;
    final int size;

    public final int[] pixels;

    public static SpriteSheet TILES = new SpriteSheet("/textures/SpriteSheet.png", 256);

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        this.pixels = new int[size * size];
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException("Could not load sprite sheet at " + path);
        }
    }

    private void load() throws IOException {
        BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
        int w = image.getWidth();
        int h = image.getHeight();
        image.getRGB(0, 0, w, h, pixels, 0, w);
    }
}
