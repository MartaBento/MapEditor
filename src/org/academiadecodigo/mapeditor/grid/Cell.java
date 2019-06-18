package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell extends Rectangle {

    private boolean filled;

    //constructor
    public Cell(int x, int y) {
        super(x, y, Grid.CELLSIZE, Grid.CELLSIZE);
    }

    public boolean isFilled() {
        return filled;
    }

    //these methods are inherited from the super
    @Override
    public void draw() {
        super.draw();
        this.filled = false;
    }

    public void fill() {
        super.fill();
        this.filled = true;
    }

}
