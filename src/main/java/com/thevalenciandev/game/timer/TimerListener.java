package com.thevalenciandev.game.timer;

public interface TimerListener {

    void onUpdate();
    void onRender();

    void onSecondElapsed(String info);
}
