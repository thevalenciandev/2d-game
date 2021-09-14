package com.thevalenciandev.game.graphics;

import com.thevalenciandev.game.level.tile.Tile;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    private static final int MAP_SIZE = 8; // 8x8 tiles
    private static final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public final int width;
    public final int height;
    private final int[] pixels; // use a 1 dimensional array
    private final int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private int xOffset, yOffset;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        Random random = new Random();
        for (int i = 0; i < tiles.length; i++) {
            // Pick any color from 0 (black) to white (0xFFFFFF)
            tiles[i] = random.nextInt(0xFFFFFF);
            tiles[0] = 0;
        }
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void copyPixelsOnto(int[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = pixels[i];
        }
    }
//    public void render(int xOffset, int yOffset) {
//        for (int y = 0; y < height; y++) {
//            int yp = y + yOffset;
//            if (yp < 0 || yp >= height) continue;
//            for (int x = 0; x < width; x++) {
//                int xp = x + xOffset;
//                if (xp < 0 || xp >= width) continue;
//                // Below we do the equivalent of (x / TILE_SIZE) + (y / TILE_SIZE) * MAP_SIZE;
//                // basically the same as x + y * width but scaled to the tile size
//                // now render the pixel
//                pixels[xp + yp * width] = Sprite.GRASS.pixels[(x & 15) + (y & 15) * Sprite.GRASS.size];
//            }
//        }
//    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.size; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.size; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.size || xa >= width || ya < 0 || ya >= height)
                    break; // only render the tiles that we see on the screen (don't consume extra resources for nothin')
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.size];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
