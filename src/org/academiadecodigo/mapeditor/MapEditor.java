package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.grid.Cell;
import org.academiadecodigo.mapeditor.grid.Cursor;
import org.academiadecodigo.mapeditor.grid.CursorDirection;
import org.academiadecodigo.mapeditor.grid.Grid;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class MapEditor {

    private Grid grid;
    private Cursor cursor;
    private boolean paitingCell;
    public LinkedList<Cell> cellGrid;
    private MapKeyboard mapKeyboard;

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

    public void unFillCell() {
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

}
