package org.academiadecodigo.mapeditor;/* Academia de Código
Map Editor - Exercise with Simple Gfx, emulates a paint program.
 */

import org.academiadecodigo.mapeditor.grid.Cursor;
import org.academiadecodigo.mapeditor.grid.CursorDirection;
import org.academiadecodigo.mapeditor.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(6,6);
        Cursor cursor = new Cursor(10,10);
    }
}
