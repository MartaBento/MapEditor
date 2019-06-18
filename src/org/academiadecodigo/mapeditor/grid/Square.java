package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Square extends Rectangle {

    //properties
    private int x;
    private int y;
    private boolean filled;

    //constructor
    public Square(int x, int y) {
        super(x, y, Grid.CELLSIZE, Grid.CELLSIZE);
    }

    //these methods are inherited from the super

    @Override
    public void draw() {
        super.draw();
        filled = false; // if the square is draw, it's not filled;
    }

    public void fill() {
        super.fill();
        filled = true; // if the square is filled, then it's not drawn.
    }

}
