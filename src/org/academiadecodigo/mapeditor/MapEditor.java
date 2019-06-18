package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.grid.Cell;
import org.academiadecodigo.mapeditor.grid.Cursor;
import org.academiadecodigo.mapeditor.grid.CursorDirection;
import org.academiadecodigo.mapeditor.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created on 18/06/2019.
 */
public class MapEditor {

    private Grid grid;
    private Cursor cursor;
    private boolean paitingCell;
    public LinkedList<Cell> cellGrid;


    public void init() {
        grid = new Grid();
        cellGrid = grid.getCell();
        grid.init();
        drawGrid();
        cursor = new Cursor(new Cell(Grid.PADDING, Grid.PADDING));
        cursor.init();
    }

    public void drawGrid () {
        for (Cell rectangle : cellGrid) {
            rectangle.draw();
        }
    }

    public void unfillCell() {
        for (Cell rectangle : cellGrid) {
            rectangle.draw();
        }
    }

    public void fillCell() {
        for (Cell rectangle : cellGrid) {
            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && !rectangle.isFilled()) {
                rectangle.fill();
                return;
            }

            if (rectangle.getY() == cursor.y() && rectangle.getX() == cursor.x() && rectangle.isFilled()) {
                rectangle.draw();
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

    public class MapEditorKeyboard implements KeyboardHandler {

        private MapEditor mapEditor;

        public MapEditorKeyboard(MapEditor mapEditor) {
            this.mapEditor = mapEditor;
        }

        public void init() {
            setupKeyboardListeners();
            mapEditor.init();
        }

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
        public void keyPressed(KeyboardEvent keyboardEvent) {

            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_UP:
                    mapEditor.paintCell(CursorDirection.UP);
                    break;

                case KeyboardEvent.KEY_DOWN:
                    mapEditor.paintCell(CursorDirection.DOWN);
                    break;

                case KeyboardEvent.KEY_LEFT:
                    mapEditor.paintCell(CursorDirection.LEFT);
                    break;

                case KeyboardEvent.KEY_RIGHT:
                    mapEditor.paintCell(CursorDirection.RIGHT);
                    break;

                case KeyboardEvent.KEY_A:
                    mapEditor.setPaitingCell(true);
                    mapEditor.fillCell();
                    break;

                case KeyboardEvent.KEY_Z:
                    mapEditor.unfillCell();
                    break;

            }

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }
    }

}
