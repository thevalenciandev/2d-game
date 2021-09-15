package com.thevalenciandev.game.entity;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.level.Level;

import java.util.Random;

/**
 * Doesn't have to have a Sprite, but does need to be rendered.
 * They can move, which is why we have an x and y coordinates.
 */
public abstract class Entity {

    private static final Random random = new Random();

    protected Level level;
    public int x, y;
    private boolean removed = false;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Screen screen) {

    }

    public void remove() {
        removed = true; // Remove from Level
    }

    /**
     * @return whether it has been removed from the Level or not.
     */
    public boolean isRemoved() {
        return removed;
    }
}
