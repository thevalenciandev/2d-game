package com.thevalenciandev.game.graphics;

/**
 * An image within the SpriteSheet. The SpriteSheet is a grid,
 * and each cell is a Sprite. It can represent the characters
 * in the game, some landscape, etc.
 */
public class Sprite {

    final int size;
    private int x;
    private int y;
    private SpriteSheet spriteSheet;

    public final int[] pixels;

    public static final Sprite GRASS = new Sprite(16, 0, 0, SpriteSheet.TILES);
    public static final Sprite VOID_BLUE = new Sprite(16, 0x1B87E0);

    // Each player image is of size 32 x 32
    public static final Sprite PLAYER_DOWN =  new Sprite(32, 0, 0, SpriteSheet.PLAYER);
    public static final Sprite PLAYER_LEFT =  new Sprite(32, 0, 1, SpriteSheet.PLAYER);
    public static final Sprite PLAYER_RIGHT = new Sprite(32, 0, 2, SpriteSheet.PLAYER);
    public static final Sprite PLAYER_UP =    new Sprite(32, 0, 3, SpriteSheet.PLAYER);

    /**
     * @param size
     * @param x starting point on the x axis (in tile precision)
     * @param y starting point on the y axis (in tile precision)
     * @param spriteSheet the sheet to get the Sprite from
     */
    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        this.size = size;
        this.x = x * size;
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        this.pixels = new int[size * size];
        load();
    }

    public Sprite(int size, int color) {
        this.size = size;
        this.pixels = new int[size * size];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < size * size; i++) {
            pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                // this will copy the sprite's pixels from the sheet (the exact cell in the sprite sheet)
                pixels[x + y * size] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.size];
            }
        }
    }
}
