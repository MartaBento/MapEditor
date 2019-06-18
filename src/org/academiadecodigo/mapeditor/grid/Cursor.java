package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
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
        fill();
    }

    public void moveInDirection(CursorDirection direction, int distance) {

        switch (direction) {

            case UP:
                moveUp(distance);
                break;

            case DOWN:
                moveDown(distance);
                break;

            case LEFT:
                moveLeft(distance);
                break;

            case RIGHT:
                moveRight(distance);
                break;
        }
    }

    public void moveUp(int distance) {
        cursor.translate(Grid.CELLSIZE, - distance);
    }

    public void moveDown(int distance) {

    }

    public void moveLeft(int distance) {

    }

    public void moveRight(int distance) {

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
        cursor.setColor(Color.DARK_GRAY);
        filled = true;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            cursor.moveInDirection(CursorDirection.UP, Grid.CELLSIZE + Grid.PADDING);
            System.out.println("Up");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            System.out.println("Down");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            System.out.println("Left");
        } else if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            System.out.println("Right");
        }
    }
// rectangle = new Rectangle(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, CELLSIZE, CELLSIZE); // x, y, width, height

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
