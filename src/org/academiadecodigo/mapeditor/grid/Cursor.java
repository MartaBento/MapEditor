package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Cursor extends Square implements KeyboardHandler {

    //properties
    private int x;
    private int y;
    private boolean filled;
    private Cursor cursor;

    //constructor
    public Cursor(int x, int y) {
        super(x, y);
        setupKeyboardListeners();
    }

    public void move(CursorDirection direction) {

        switch (direction) {
            case UP:
                cursor.translate(1, 1);
        }

    }

    // keyboard events for the cursor

    private void setupKeyboardListeners() {
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent eventUp = new KeyboardEvent();
        eventUp.setKey(KeyboardEvent.KEY_UP);
        eventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventUp);

        KeyboardEvent eventDown = new KeyboardEvent();
        eventDown.setKey(KeyboardEvent.KEY_DOWN);
        eventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventDown);


        KeyboardEvent eventLeft = new KeyboardEvent();
        eventLeft.setKey(KeyboardEvent.KEY_LEFT);
        eventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventLeft);


        KeyboardEvent eventRight = new KeyboardEvent();
        eventRight.setKey(KeyboardEvent.KEY_RIGHT);
        eventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventRight);
    }

    @Override
    public void draw() {
        super.draw();
        filled = false;
    }

    @Override
    public void fill() {
        super.fill();
        filled = true;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            cursor.move(CursorDirection.UP);
            System.out.println("Tecla Up");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            System.out.println("Tecla Down");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            System.out.println("Tecla Left");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            System.out.println("Tecla Right");
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
