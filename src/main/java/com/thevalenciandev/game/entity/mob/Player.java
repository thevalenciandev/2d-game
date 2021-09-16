package com.thevalenciandev.game.entity.mob;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;
import com.thevalenciandev.game.input.Keyboard;

public class Player extends Mob {

    private final Keyboard keyboard;

    public Player(int x, int y, Keyboard keyboard) {
        super(x, y, Sprite.PLAYER_DOWN);
        this.keyboard = keyboard;
    }

    public Player(Keyboard keyboard) {
        this(0, 0, keyboard);
    }

    @Override
    public void update() {
        int xa = 0, ya = 0;
        if (keyboard.up) ya--;
        if (keyboard.down) ya++;
        if (keyboard.left) xa--;
        if (keyboard.right) xa++;

        if (xa != 0 || ya != 0)
            super.move(xa, ya);
    }

    @Override
    public void render(Screen screen) {
        // Make sure the player is centered, by moving it just half a tile (32/2)
        this.sprite = switch (dir) {
            case SOUTH -> Sprite.PLAYER_DOWN;
            case NORTH -> Sprite.PLAYER_UP;
            case EAST -> Sprite.PLAYER_RIGHT;
            case WEST -> Sprite.PLAYER_LEFT;
        };

        screen.renderPlayer(this.x - 16, this.y - 16, this.sprite);
    }
}
