package com.thevalenciandev.game.level;

import com.thevalenciandev.game.level.tile.Tile;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();
    protected int[] tiles;

    public RandomLevel(int width, int height) {
        super(width, height);
        tiles = new int[width * height];
    }

    @Override
    protected void loadLevel(String path) {
        // Nothing to load. It's just random tiles.
    }

    @Override
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(4); // 0 to 3
            }
        }
    }

    // TODO: move this into a factory
    private Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.VOID_TILE; // if out of the map, just render a void tile

        if (tiles[x + y * width] == 0) return Tile.GROUND_TILE;
        if (tiles[x + y * width] == 1) return Tile.LAVA_TILE;
        return Tile.VOID_TILE;
    }

}
