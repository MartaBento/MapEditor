package org.academiadecodigo.mapeditor;

import org.academiadecodigo.mapeditor.grid.CursorDirection;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class MapKeyboard implements KeyboardHandler {

    private MapEditor mapEditor;

    public MapKeyboard(MapEditor mapEditor) {
        this.mapEditor = mapEditor;
    }

    public void init() {
        setupKeyboardListeners();
        mapEditor.init();
    }

    private void setupKeyboardListeners() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent eventUp = new KeyboardEvent();
        eventUp.setKey(KeyboardEvent.KEY_UP);
        eventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventUp);

        KeyboardEvent eventDown = new KeyboardEvent();
        eventDown.setKey(KeyboardEvent.KEY_DOWN);
        eventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventDown);


        KeyboardEvent eventLeft = new KeyboardEvent();
        eventLeft.setKey(KeyboardEvent.KEY_LEFT);
        eventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventLeft);


        KeyboardEvent eventRight = new KeyboardEvent();
        eventRight.setKey(KeyboardEvent.KEY_RIGHT);
        eventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(eventRight);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                mapEditor.paintCell(CursorDirection.UP);
                System.out.println("Up");
                break;

            case KeyboardEvent.KEY_DOWN:
                mapEditor.paintCell(CursorDirection.DOWN);
                System.out.println("Down");
                break;

            case KeyboardEvent.KEY_LEFT:
                mapEditor.paintCell(CursorDirection.LEFT);
                System.out.println("Left");
                break;

            case KeyboardEvent.KEY_RIGHT:
                mapEditor.paintCell(CursorDirection.RIGHT);
                System.out.println("Right");
                break;

            case KeyboardEvent.KEY_A:
                mapEditor.setPaitingCell(true);
                mapEditor.fillCell();
                break;

            case KeyboardEvent.KEY_Z:
                mapEditor.unFillCell();
                break;

            case KeyboardEvent.KEY_S:
                mapEditor.save();
                break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
