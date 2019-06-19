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

    public void unFillCell() {
        for (Cell rectangle : cellGrid) {
            rectangle.draw();
        }
    }

    public void fillCell() {
        for (Cell rectangle : cellGrid) {
            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && !rectangle.isFilled()) {
                rectangle.fill();
                rectangle.setColor(Color.DARK_GRAY);
                return;
            }

            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && rectangle.isFilled()) {
                rectangle.draw();
                rectangle.fill();
                rectangle.setColor(Color.DARK_GRAY);
            }
        }
    }

    public void paintCell(CursorDirection direction) {

        if (paitingCell) {
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
            fileWriter = new FileWriter("save/savedFile");

            for (Cell cell : grid.getCell()) {

                if (cell.isFilled()) {
                    fileWriter.write(1);
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

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                paintCell(CursorDirection.UP);
                System.out.println("Up");
                break;

            case KeyboardEvent.KEY_DOWN:
                paintCell(CursorDirection.DOWN);
                System.out.println("Down");
                break;

            case KeyboardEvent.KEY_LEFT:
                paintCell(CursorDirection.LEFT);
                System.out.println("Left");
                break;

            case KeyboardEvent.KEY_RIGHT:
                paintCell(CursorDirection.RIGHT);
                System.out.println("Right");
                break;

            case KeyboardEvent.KEY_A:
                setPaitingCell(true);
                fillCell();
                System.out.println("Painting cell");
                break;

            case KeyboardEvent.KEY_Z:
                unFillCell();
                System.out.println("Unpainting cell");
                break;

            /*case KeyboardEvent.KEY_S:
                save();
                break;
            */
        }

    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}
