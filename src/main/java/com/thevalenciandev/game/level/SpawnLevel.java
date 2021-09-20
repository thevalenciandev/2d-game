package com.thevalenciandev.game.level;

import com.thevalenciandev.game.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

    // Adding ff at the front to handle the alpha channel
    // ff means is 100% opaque. 00 would be 100% transparent.
    private static final int COLOR_GREEN = 0xffFF00;
    private static final int COLOR_LAVA =  0xff7F0000;
    private static final int COLOR_BROWN = 0xff7F3300;

    private int[] pixels;

    public SpawnLevel(String path) {
        super(path);
    }

    @Override
    protected void loadLevel(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(SpawnLevel.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException("Could not load level from " + path, e);
        }
        int w = image.getWidth();
        int h = image.getHeight();
        tiles = new Tile[w * h];
        pixels = new int[w * h];
        // we store which color every pixel is
        image.getRGB(0, 0, w, h, pixels, 0, w);
    }

    @Override
    protected void generateLevel() {
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] == COLOR_GREEN) tiles[i] = Tile.GRASS;
            else if (pixels[i] == COLOR_LAVA) tiles[i] = Tile.LAVA_TILE;
            else if (pixels[i] == COLOR_BROWN) tiles[i] = Tile.GROUND_TILE;
        }
        System.out.println("Blah");
    }
}
