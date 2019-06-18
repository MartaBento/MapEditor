package org.academiadecodigo.mapeditor.grid;

/**
 * Created on 18/06/2019.
 */
public enum CursorDirection {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public void moveInDirection(CursorDirection direction, int distance) {
        switch (direction) {
            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(direction);
                break;
            case LEFT:
                moveLeft(direction);
                break;
            case RIGHT:
                moveRight(direction);
                break;
        }
    }

    public void moveUp(int distance) {

    }

    public void moveDown(int distance) {

    }

    public void moveLeft(int distance) {

    }

    public void moveRight(int distance) {

    }


}

    /*
    public void moveUp(int dist) {
        if (picture.getY() - dist >= 170) {
            picture.translate(0, -dist);
        } else {
            moveDown(dist);
        }
    }*/

