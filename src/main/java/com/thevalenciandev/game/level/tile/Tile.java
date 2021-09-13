package com.thevalenciandev.game.level.tile;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;

/**
 * A tile represents every individual cell in the SpriteSheet.
 * We will attach a Sprite to each of them.
 */
public class Tile {

    public final Sprite sprite;
    public int x, y;

    public static final Tile GRASS = new GrassTile(Sprite.GRASS);
    public static final Tile VOID_TILE = new VoidTile(Sprite.VOID_BLUE);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @param x in tile precision
     * @param y in tile precision
     * @param screen
     */
    public void render(int x, int y, Screen screen) {
    }

    /**
     * If solid = true, we can pass through this tile.
     * In other words, if solid then this tile is collidable.
     */
    public boolean isSolid() {
        return false;
    }
}
