package com.thevalenciandev.game.entity.mob;

import com.thevalenciandev.game.entity.Entity;
import com.thevalenciandev.game.graphics.Sprite;

/**
 * Creatures that move.
 */
public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0; // 0 - north, 1 - east, 2 - south, 3 - west
    protected boolean moving = false;

    public Mob(int x, int y) {
        super(x, y);
    }

    public void move(int xa, int ya) {
        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

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
