package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.grid.Cell;
import org.academiadecodigo.mapeditor.grid.Cursor;
import org.academiadecodigo.mapeditor.grid.CursorDirection;
import org.academiadecodigo.mapeditor.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class MapEditor implements KeyboardHandler {

    private Grid grid;
    private Cursor cursor;
    private boolean paitingCell;
    public LinkedList<Cell> cellGrid;
    private Keyboard keyboard = new Keyboard(this);

    public void init() {
        grid = new Grid();
        cursor = new Cursor(new Cell(Grid.PADDING, Grid.PADDING));
        cursor.init();
        cellGrid = grid.getCell();
        grid.init();
        drawGrid();
        setupKeyboardListeners();
    }

    public void drawGrid () {
        for (Cell rectangle : cellGrid) {
            rectangle.draw();
        }
    }

    public void fillCell() {
        for (Cell rectangle : cellGrid) {
            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && !rectangle.isFilled()) {
                rectangle.fill();
                rectangle.setColor(Color.BLACK);
                return;
            }

            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && rectangle.isFilled()) {
                rectangle.fill();
                rectangle.setColor(Color.BLACK);
            }
        }
    }

    public void unFillCell() {
        for (Cell rectangle : cellGrid) {
            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && rectangle.isFilled()) {
                rectangle.draw();
                return;
            }

            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && rectangle.isFilled()) {
                rectangle.draw();
                return;
            }
        }
    }

    public void paintCell(CursorDirection direction) {

        if (!paitingCell) {
            fillCell();
        }

        switch (direction) {

            case UP:
                cursor.moveInDirection(CursorDirection.UP);
                break;
            case DOWN:
                cursor.moveInDirection(CursorDirection.DOWN);
                break;
            case LEFT:
                cursor.moveInDirection(CursorDirection.LEFT);
                break;
            case RIGHT:
                cursor.moveInDirection(CursorDirection.RIGHT);
                break;
        }
    }

    public void setPaitingCell(boolean paitingCell) {
        this.paitingCell = paitingCell;
    }

    public void save () {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("savedFile");

            for (Cell cell : grid.getCell()) {

                if (cell.isFilled()) {
                    fileWriter.write(1);
                    System.out.println("File saved");
                    continue;
                }
                fileWriter.write(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fileWriter);
        }
    }

    private void closeStream(Closeable stream) {

        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* keyboard config */

    private void setupKeyboardListeners() {

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

        KeyboardEvent eventSpace = new KeyboardEvent();
        eventSpace.setKey(KeyboardEvent.KEY_SPACE);
        eventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventSpace);

        KeyboardEvent eventZ = new KeyboardEvent();
        eventZ.setKey(KeyboardEvent.KEY_Z);
        eventZ.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventZ);

        KeyboardEvent eventS = new KeyboardEvent();
        eventS.setKey(KeyboardEvent.KEY_S);
        eventS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventS);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                cursor.moveInDirection(CursorDirection.UP);
                System.out.println("Key Up");
                break;

            case KeyboardEvent.KEY_DOWN:
                cursor.moveInDirection(CursorDirection.DOWN);
                System.out.println("Key down");
                break;

            case KeyboardEvent.KEY_LEFT:
                cursor.moveInDirection(CursorDirection.LEFT);
                System.out.println("Key left");
                break;

            case KeyboardEvent.KEY_RIGHT:
                cursor.moveInDirection(CursorDirection.RIGHT);
                System.out.println("Key right");
                break;

            case KeyboardEvent.KEY_SPACE:
                setPaitingCell(true);
                fillCell();
                System.out.println("Key Space - Painting cell");
                break;

            case KeyboardEvent.KEY_Z:
                setPaitingCell(false);
                unFillCell();
                System.out.println("Key Z - Unpainting cell");
                break;

            case KeyboardEvent.KEY_S:
                save();
                break;
        }

    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}
