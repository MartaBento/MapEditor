package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    // these properties need to have a fixed value all along the program since we will create a grid with rows & columns, but these values will stay fixed.
    static final int CELLSIZE = 40;
    static final int PADDING = 10;

    //grid properties
    private int rows;
    private int columns;

    private Rectangle rectangle;
    private Cursor cursor;

    //constructor
    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        init();
    }

    public void init() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                rectangle = new Rectangle(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, CELLSIZE, CELLSIZE); // x, y, width, height
                rectangle.draw();
                rectangle.setColor(Color.LIGHT_GRAY);
            }
        }

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