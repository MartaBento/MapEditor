package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cursor extends Rectangle {

    //properties
    private int x;
    private int y;
    private boolean filled;
    private Cursor cursor;

    //constructor
    public Cursor(int x, int y) {
        super(x, y, Grid.CELLSIZE, Grid.CELLSIZE);
    }

    @Override
    public void draw() {
        super.draw();
        filled = false;
    }

    @Override
    public void fill() {
        super.fill();
        filled = true;
    }

}
