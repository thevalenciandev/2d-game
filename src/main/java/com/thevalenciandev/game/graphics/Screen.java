package com.thevalenciandev.game.graphics;

import com.thevalenciandev.game.level.tile.Tile;

import java.util.Arrays;

public class Screen {

    private static final int MAP_SIZE = 8; // 8x8 tiles
    private static final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public final int width;
    public final int height;
    private final int[] pixels; // use a 1 dimensional array
    private final int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private int xOffset, yOffset;

    public Screen(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

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

    public void renderPlayer(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        int playerSize = 32;
        for (int y = 0; y < playerSize; y++) {
            int ya = y + yp;
            for (int x = 0; x < playerSize; x++) {
                int xa = x + xp;
                if (xa < -playerSize || xa >= width || ya < 0 || ya >= height)
                    break; // only render the tiles that we see on the screen (don't consume extra resources for nothin')
                if (xa < 0) xa = 0;
                int color = sprite.pixels[x + y * playerSize];
                if (color != 0xFFFF00FF) // 0xFFFF00FF is the background pink, but we add FF at the front because we're using an alpha channel too
                    pixels[xa + ya * width] = color;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
