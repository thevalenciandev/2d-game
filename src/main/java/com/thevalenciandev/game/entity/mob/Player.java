package com.thevalenciandev.game.entity.mob;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;
import com.thevalenciandev.game.input.Keyboard;

public class Player extends Mob {

    public enum FlipInstruction {
        NO_FLIP, FLIP_X, FLIP_Y, FLIP_XY
    }

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
        FlipInstruction flip = FlipInstruction.NO_FLIP;
        // Make sure the player is centered, by moving it just half a tile (32/2)
        if (dir == Direction.NORTH) {
            sprite = !walking ? Sprite.PLAYER_UP : first() ? Sprite.PLAYER_UP_1 : Sprite.PLAYER_UP_2;
        } else if (dir == Direction.EAST || dir == Direction.WEST) {
            sprite = !walking ? Sprite.PLAYER_RIGHT : first() ? Sprite.PLAYER_RIGHT_1 : Sprite.PLAYER_RIGHT_2;
            if (dir == Direction.WEST) {
                flip = FlipInstruction.FLIP_X;
            }
        } else if (dir == Direction.SOUTH) {
            sprite = !walking ? Sprite.PLAYER_DOWN : first() ? Sprite.PLAYER_DOWN_1 : Sprite.PLAYER_DOWN_2;
        }

        screen.renderPlayer(this.x - 16, this.y - 16, this.sprite, flip);
    }

    private boolean first() {
        return animate % 20 > 10; // half the time
    }
}
