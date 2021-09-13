package com.thevalenciandev.game.level.tile;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        // x and y in pixels. we need to line them up to what the screen expects (in pixels, not tile precision)
        screen.renderTile(x << 4, y << 4, this);
    }
}
