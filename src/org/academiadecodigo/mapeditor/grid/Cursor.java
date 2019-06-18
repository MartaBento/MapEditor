package org.academiadecodigo.mapeditor.grid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Cursor {

    private Cell userCursor;

    public Cursor (Cell userCursor) {
        this.userCursor = userCursor;
    }

    public void init() {
        userCursor.setColor(Color.BLUE);
        userCursor.fill();
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
                if (y() >= (Grid.CELLSIZE * Grid.COLUMNS + Grid.PADDING) - Grid.CELLSIZE) {
                    return false;
                }
                return true;

            case LEFT:
                if (x() <= Grid.PADDING) {
                    return false;
                }
                return true;

            case RIGHT:
                if (x() >= (Grid.CELLSIZE * Grid.ROWS + Grid.PADDING) - Grid.CELLSIZE) {
                    return false;
                }
                return true;
        }
        return true;
    }

    // the cursor moves allowed on our grid

    public void moveInDirection (CursorDirection direction) {

        if (direction == CursorDirection.UP && canMoveInDirection(direction)) {
            userCursor.translate(0, -Grid.CELLSIZE);
        }
        if (direction == CursorDirection.DOWN && canMoveInDirection(direction)) {
            userCursor.translate(0, -Grid.CELLSIZE);
        }

        if (direction == CursorDirection.LEFT && canMoveInDirection(direction)) {
            userCursor.translate(-Grid.CELLSIZE, 0);
        }

        if (direction == CursorDirection.RIGHT && canMoveInDirection(direction)) {
            userCursor.translate(Grid.CELLSIZE, 0);
        }
    }

    public int x() {
        return userCursor.getX();
    }

    public int y() {
        return userCursor.getY();
    }

    public Cell getCursor() {
        return userCursor;
    }

    public void setCursor(Cell cursor) {
        this.userCursor = cursor;
    }
}


