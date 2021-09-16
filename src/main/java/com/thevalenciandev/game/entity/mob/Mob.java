package com.thevalenciandev.game.entity.mob;

import com.thevalenciandev.game.entity.Entity;
import com.thevalenciandev.game.graphics.Sprite;

/**
 * Creatures that move.
 */
public abstract class Mob extends Entity {

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }
    protected Sprite sprite;
    protected Direction dir = Direction.SOUTH;
    protected boolean moving = false;

    public Mob(int x, int y, Sprite sprite) {
        super(x, y);
        this.sprite = sprite;
    }

    public void move(int xa, int ya) {
        if (xa > 0) dir = Direction.EAST;
        if (xa < 0) dir = Direction.WEST;
        if (ya > 0) dir = Direction.SOUTH;
        if (ya < 0) dir = Direction.NORTH;

        if (collision()) return;

        this.x += xa;
        this.y += ya;
    }

    public void update() {
    }

    private boolean collision() {
        return false;
    }

}
