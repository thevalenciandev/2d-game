package com.thevalenciandev.game;

import com.thevalenciandev.game.entity.mob.Player;
import com.thevalenciandev.game.graphics.Screen;
import com.thevalenciandev.game.input.Keyboard;
import com.thevalenciandev.game.level.Level;
import com.thevalenciandev.game.level.RandomLevel;
import com.thevalenciandev.game.timer.TimerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable, TimerListener {

    public static int WIDTH = 300; // pixels
    public static int HEIGHT = WIDTH / 16 * 9; // 16/9 aspect ratio
    public static int SCALE = 3;

    private final JFrame frame = new JFrame();
    // image is our final rendered view. We deal with non-scaled width and height
    // so considerably fewer pixels to compute, which will make our game faster
    // RGB type means our image will NOT have alpha
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    // Raster is like a data structure that represents a rectangular array of pixels
    // Basically our image is an array of pixels. We can safely cast to DataBufferInt because the type is TYPE_INT_RGB
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private final Screen screen = new Screen(WIDTH, HEIGHT, pixels);
    private final Keyboard keyboard = new Keyboard();
    private final com.thevalenciandev.game.timer.Timer timer;
    private final Level level = new RandomLevel(64, 64);
    private final Player player = new Player(keyboard);

    private Thread thread;

    public Game() {
        // size in pixels: width * height = (300*3)*((300/16*9)*3)
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame.setResizable(false); // avoid graphical errors
        frame.setTitle("Game test");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center window into the screen
        frame.setVisible(true);

        timer = new com.thevalenciandev.game.timer.Timer(this);
        addKeyListener(keyboard);
    }

    public synchronized void start() {
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        timer.stop();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Exception while stopping", e);
        }
    }

    @Override
    public void run() {
        requestFocus();
        timer.start();
    }

    @Override
    public void onUpdate() {
        keyboard.update();
        player.update();
    }

    @Override
    public void onRender() {
        render();
    }

    @Override
    public void onSecondElapsed(String info) {
        System.out.println(info);
        frame.setTitle("Game   |   " + info);
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy(); // retrieve it from the Canvas
        if (bs == null) {
            // triple buffering, one for the actual window and 2 extra buffers
            // to render up to 2 images in parallel (for speed improvement)
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        level.render(player.x, player.y, screen);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Verdana", 0, 50));
//        g.drawString("X: " + player.x + " Y: " + player.y, 350, 300);
        g.dispose();
        bs.show(); // swap buffers (make the next available (computed) buffer visible
    }

    public static void main(String[] args) {
        var game = new Game();
        game.start();
    }
}
