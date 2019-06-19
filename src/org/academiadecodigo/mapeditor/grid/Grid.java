package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;

public class Grid {

    // these properties need to have a fixed value all along the program since we will create a grid with rows & columns, but these values will stay fixed.
    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELLSIZE = 25;
    public static final int PADDING = 10;

    // grid width = cellsize * rows + padding
    // grid height = cellsize * columns + padding

    private LinkedList<Cell> cellGrid = new LinkedList<>();

    private Cell createGrid(int x, int y) {
        return new Cell(x, y);
    }

    public void init() {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (j == 0 && i == 0) {
                    cellGrid.add(createGrid(PADDING, PADDING));
                    continue;
                }
                cellGrid.add(createGrid(CELLSIZE * i + PADDING, CELLSIZE * j + PADDING));
            }
        }

    }

    public LinkedList<Cell> getCell() {
        return cellGrid;
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    public static int getCELLSIZE() {
        return CELLSIZE;
    }

    public static int getPADDING() {
        return PADDING;
    }

    public LinkedList<Cell> getCellGrid() {
        return cellGrid;
    }

    public void setCellGrid(LinkedList<Cell> cellGrid) {
        this.cellGrid = cellGrid;
    }
}