package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Cursor extends Cell implements KeyboardHandler {

    //properties
    private int x;
    private int y;
    private boolean filled;
    private Cursor cursor;
    private int rows;
    private int columns;
    private Grid grid;
    private Cell cell;

    //constructor
    public Cursor(int x, int y) {
        super(x, y);
        setupKeyboardListeners();
    }

    public void move(CursorDirection direction) {

        switch (direction) {
            case UP:

        }

    }

    /*
    public void playerMoveDirection(GridDirection direction, int distance) {

        switch (direction) {
            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case RIGHT:
                moveRight(distance);
                break;
            case LEFT:
                moveLeft(distance);
                break;
        }
    }

    public void moveUp(int dist) {
        if (picture.getY() - dist >= 170) {
            picture.translate(0, -dist);
        } else {
            moveDown(dist);
        }
    }
     */

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
            System.out.println("Up");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            System.out.println("Down");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            System.out.println("Left");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            System.out.println("Right");
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
