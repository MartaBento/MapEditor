package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.grid.Cell;
import org.academiadecodigo.mapeditor.grid.Cursor;
import org.academiadecodigo.mapeditor.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;
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

    public void paintCell(Cursor.CursorDirection cursorDirection) {

        if (!cellPainted) {
            fillCell();
        }

        switch (cursorDirection) {

            case UP:
                cursor.moveInDirection(Cursor.CursorDirection.UP);
                break;
            case DOWN:
                cursor.moveInDirection(Cursor.CursorDirection.DOWN);
                break;
            case LEFT:
                cursor.moveInDirection(Cursor.CursorDirection.LEFT);
                break;
            case RIGHT:
                cursor.moveInDirection(Cursor.CursorDirection.RIGHT);
                break;
        }
    }

    public void setPaitingCell(boolean paitingCell) {
        this.cellPainted = paitingCell;
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
                KeyboardEvent.KEY_L,
        };

        for (int key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
    }

    public void save() {

        FileWriter fileWriter = null;

        try {

            fileWriter = new FileWriter("resources/savedfile.txt");

            for (Cell cell : grid.getCell()) {
                if (cell.isFilled()) {
                    fileWriter.write(1);
                    System.out.println("File saved.");
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

    public void load() {

        FileReader fileReader = null;

        try {

            fileReader = new FileReader("resources/savedfile.txt");
            LinkedList<Integer> list = new LinkedList<>();

            int i;

            while ((i = fileReader.read()) != -1) {
                list.add(i);
            }

            for (int j = 0; j < grid.getCell().size(); j++) {
                if (list.get(j) == 1) {
                    grid.getCell().get(j).fill();
                    continue;
                }
                grid.getCell().get(j).draw();
                System.out.println("File loaded.");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            closeStream(fileReader);
        }
    }

    private void closeStream(Closeable stream) {

        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                cursor.moveInDirection(Cursor.CursorDirection.UP);
                break;

            case KeyboardEvent.KEY_DOWN:
                cursor.moveInDirection(Cursor.CursorDirection.DOWN);
                break;

            case KeyboardEvent.KEY_LEFT:
                cursor.moveInDirection(Cursor.CursorDirection.LEFT);
                break;

            case KeyboardEvent.KEY_RIGHT:
                cursor.moveInDirection(Cursor.CursorDirection.RIGHT);
                break;

            case KeyboardEvent.KEY_SPACE:
                setPaitingCell(true);
                fillCell();
                break;

            case KeyboardEvent.KEY_Z:
                setPaitingCell(false);
                unFillCell();
                break;

            case KeyboardEvent.KEY_S:
                save();
                break;

            case KeyboardEvent.KEY_L:
                load();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            setPaitingCell(false);
        }

    }

}
