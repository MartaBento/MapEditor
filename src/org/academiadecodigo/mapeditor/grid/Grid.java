package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;

public class Grid {

    // these properties need to have a fixed value all along the program since we will create a grid with rows & columns, but these values will stay fixed.
    public static final int CELLSIZE = 40;
    public static final int PADDING = 10;

    //grid properties
    private int rows = 10;
    private int columns = 10;

    private LinkedList<Cell> cellGrid = new LinkedList<>();

    private Cell createGrid(int x, int y) {
        return new Cell(x, y);
    }

    public void init() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }



}