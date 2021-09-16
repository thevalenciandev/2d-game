package com.thevalenciandev.game.entity.mob;

import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.graphics.Sprite;
import com.thevalenciandev.game.input.Keyboard;

public class Player extends Mob {

    private final Keyboard keyboard;

    public Player(int x, int y, Keyboard keyboard) {
        super(x, y);
        this.keyboard = keyboard;
    }

    public Player(Keyboard keyboard) {
        super(0, 0);
        this.keyboard = keyboard;
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
        // Make sure the player is centered, by moving it just one tile (as we're rendering 4 separate chunks)
        int xx = this.x - 16;
        int yy = this.y - 16;
        screen.renderPlayer(xx, yy, Sprite.PLAYER_0);
        screen.renderPlayer(xx + 16, yy, Sprite.PLAYER_1);
        screen.renderPlayer(xx, yy + 16, Sprite.PLAYER_2);
        screen.renderPlayer(xx + 16, yy + 16, Sprite.PLAYER_3);
    }
}
