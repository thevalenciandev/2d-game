package com.thevalenciandev.game.level;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.level.tile.Tile;

public class Level {

    public static final int TILE_SIZE = 16; //FIXME: we should retrieve it from the tile
    protected int width, height;
    protected int[] tiles;

    /**
     * @param width in tiles
     * @param height in tiles
     */
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    private void loadLevel(String path) {

    }

    public void update() {

    }

    public void render() {

    }

    /**
     * @param xScroll x Position of the player
     * @param yScroll y Position of the player
     * @param screen  where we will be rendering onto
     */
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll); // set the actual location of the player on the screen
        // 16 is the size of the tiles. We want to be dealing by tiles here.

        // set the 4 corner pins
        int x0 = xScroll >> 4; // left most part of the screen for x.
        int x1 = (xScroll + screen.width + TILE_SIZE) >> 4; // right most part of the screen (we add one more tile to cover/render the whole screen)
        int y0 = yScroll >> 4; // top part of the screen
        int y1 = (yScroll + screen.height + TILE_SIZE) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
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
