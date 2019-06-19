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
    private boolean cellPainted;
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

        if (!cellPainted) {
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
        this.cellPainted = paitingCell;
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

        int[] keys = {
                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_LEFT,
                KeyboardEvent.KEY_RIGHT,
                KeyboardEvent.KEY_SPACE,
                KeyboardEvent.KEY_Z,
                KeyboardEvent.KEY_S,
        };

        for (int key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
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
