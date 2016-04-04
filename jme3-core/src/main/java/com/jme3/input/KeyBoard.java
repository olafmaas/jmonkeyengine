package com.jme3.input;

public class KeyBoard {
	
    /**
     * Enable simulation of keyboard events. Used for touchscreen input only.
     *
     * @param value True to enable simulation of keyboard events
     */
    public void setSimulateKeyboard(boolean value) {
        if (touch != null) {
            touch.setSimulateKeyboard(value);
        }
    }

    /**
     * Returns state of simulation of key events. Used for touchscreen input only.
     *
     */
    public boolean isSimulateKeyboard() {
        if (touch != null) {
            return touch.isSimulateKeyboard();
        } else {
            return false;
        }
    }
}
