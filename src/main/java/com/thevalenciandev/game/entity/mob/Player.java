package com.thevalenciandev.game.entity.mob;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;
import com.thevalenciandev.game.input.Keyboard;

public class Player extends Mob {

    private final Keyboard keyboard;
    private int animate = 0;
    private boolean walking;

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
        if (animate++ > 7500) {
            animate = 0; // reset back to 0 (to prevent overflows)
        }
        if (keyboard.up) ya--;
        if (keyboard.down) ya++;
        if (keyboard.left) xa--;
        if (keyboard.right) xa++;

        if (xa != 0 || ya != 0) {
            super.move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    @Override
    public void render(Screen screen) {
        // Make sure the player is centered, by moving it just half a tile (32/2)
        if (dir == Direction.NORTH) {
            sprite = !walking ? Sprite.PLAYER_UP : first() ? Sprite.PLAYER_UP_1 : Sprite.PLAYER_UP_2;
        } else if (dir == Direction.EAST) {
            sprite = !walking ? Sprite.PLAYER_RIGHT : first() ? Sprite.PLAYER_RIGHT_1 : Sprite.PLAYER_RIGHT_2;
        } else if (dir == Direction.SOUTH) {
            sprite = !walking ? Sprite.PLAYER_DOWN : first() ? Sprite.PLAYER_DOWN_1 : Sprite.PLAYER_DOWN_2;
        } else {
            sprite = !walking ? Sprite.PLAYER_LEFT : first() ? Sprite.PLAYER_LEFT_1 : Sprite.PLAYER_LEFT_2;
        }

        screen.renderPlayer(this.x - 16, this.y - 16, this.sprite);
    }

    private boolean first() {
        return animate % 20 > 10; // half the time
    }
}
