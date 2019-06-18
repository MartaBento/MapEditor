package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell extends Rectangle {

    //properties
    private int x;
    private int y;
    private boolean filled;

    //constructor
    public Cell(int x, int y) {
        super(x, y, Grid.CELLSIZE, Grid.CELLSIZE);
    }

    //these methods are inherited from the super
    @Override
    public void draw() {
        super.draw();
        filled = false;
    }

    public void fill() {
        super.fill();
        filled = true;
    }

}
