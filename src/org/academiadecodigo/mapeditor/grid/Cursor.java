package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Cursor {

    private Cell cursor;

    public Cursor (Cell cursor) {
        this.cursor = cursor;
    }

    public void init() {
        cursor.setColor(Color.BLUE);
        cursor.fill();
    }

    // validates our grid bounds and if the cursor can move on that specific direction when we use the keyboard
    public boolean canMoveInDirection(CursorDirection direction) {

        switch (direction) {

            case UP:
               if (y() <= Grid.PADDING) {
                   return false;
               }
               return true;

            case DOWN:
                if (y() >= (Grid.PADDING + Grid.CELLSIZE) - Grid.CELLSIZE) {
                    return false;
                }
                return true;

            case LEFT:
                if (x() <= Grid.PADDING) {
                    return false;
                }
                return true;

            case RIGHT:
                if (x() >= (Grid.PADDING + Grid.CELLSIZE) - Grid.CELLSIZE) {
                    return false;
                }
                return true;
        }
        return true;
    }

    // the cursor moves allowed on our grid

    public void moveInDirection (CursorDirection direction) {

        if (direction == CursorDirection.UP && canMoveInDirection(direction)) {
            cursor.translate(Grid.CELLSIZE, 0);
        }
        if (direction == CursorDirection.DOWN && canMoveInDirection(direction)) {
            cursor.translate(Grid.CELLSIZE, 0);
        }

        if (direction == CursorDirection.LEFT && canMoveInDirection(direction)) {
            cursor.translate(Grid.CELLSIZE, 0);
        }

        if (direction == CursorDirection.RIGHT && canMoveInDirection(direction)) {
            cursor.translate(Grid.CELLSIZE, 0);
        }
    }

    public int x() {
        return cursor.getX();
    }

    public int y() {
        return cursor.getY();
    }

}


