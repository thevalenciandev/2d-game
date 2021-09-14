package com.thevalenciandev.game.level.tile;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        // x and y in tile precision: we need to line them up to what the screen expects
        screen.renderTile(x << 4, y << 4, this);
    }
}
