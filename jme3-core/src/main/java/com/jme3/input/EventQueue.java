package com.jme3.input;

import com.jme3.app.Application;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;

public class EventQueue implements RawInputListener{
	
    private float frameTPF;
    private long firstTime = 0;
    private boolean safeMode = false;
    private final KeyInput keys;
    private long frameDelta = 0;
    private boolean eventsPermitted = false;
    private long lastUpdateTime = 0;
    private long lastLastUpdateTime = 0;
    private final MouseInput mouse;
    private final JoyInput joystick;
    private final TouchInput touch;
    
    /**
     * Initializes the InputManager.
     *
     * <p>This should only be called internally in {@link Application}.
     *
     * @param mouse
     * @param keys
     * @param joystick
     * @param touch
     * @throws IllegalArgumentException If either mouseInput or keyInput are null.
     */
    public EventQueue(MouseInput mouse, KeyInput keys, JoyInput joystick, TouchInput touch) {
        if (keys == null || mouse == null) {
            throw new IllegalArgumentException("Mouse or keyboard cannot be null");
        }

        this.keys = keys;
        this.mouse = mouse;
        this.joystick = joystick;
        this.touch = touch;

        keys.setInputListener(this);
        mouse.setInputListener(this);
        if (joystick != null) {
            joystick.setInputListener(this);
            joysticks = joystick.loadJoysticks(this);
        }
        if (touch != null) {
            touch.setInputListener(this);
        }

        firstTime = keys.getInputTimeNanos();
    }
    
    
    /**
     * Updates the <code>InputManager</code>.
     * This will query current input devices and send
     * appropriate events to registered listeners.
     *
     * @param tpf Time per frame value.
     */
    public void update(float tpf) {
        frameTPF = tpf;

        // Activate safemode if the TPF value is so small
        // that rounding errors are inevitable
        safeMode = tpf < 0.015f;

        long currentTime = keys.getInputTimeNanos();
        frameDelta = currentTime - lastUpdateTime;

        eventsPermitted = true;

        keys.update();
        mouse.update();
        if (joystick != null) {
            joystick.update();
        }
        if (touch != null) {
            touch.update();
        }

        eventsPermitted = false;

        processQueue();
        invokeUpdateActions();

        lastLastUpdateTime = lastUpdateTime;
        lastUpdateTime = currentTime;
    }
    
    private void processQueue() {
        int queueSize = inputQueue.size();
        RawInputListener[] array = rawListeners.getArray(); 

        for (RawInputListener listener : array) {
            listener.beginInput();

            for (int j = 0; j < queueSize; j++) {
                InputEvent event = inputQueue.get(j);
                if (event.isConsumed()) {
                    continue;
                }

                if (event instanceof MouseMotionEvent) {
                    listener.onMouseMotionEvent((MouseMotionEvent) event);
                } else if (event instanceof KeyInputEvent) {
                    listener.onKeyEvent((KeyInputEvent) event);
                } else if (event instanceof MouseButtonEvent) {
                    listener.onMouseButtonEvent((MouseButtonEvent) event);
                } else if (event instanceof JoyAxisEvent) {
                    listener.onJoyAxisEvent((JoyAxisEvent) event);
                } else if (event instanceof JoyButtonEvent) {
                    listener.onJoyButtonEvent((JoyButtonEvent) event);
                } else if (event instanceof TouchEvent) {
                    listener.onTouchEvent((TouchEvent) event);
                } else {
                    assert false;
                }
            }

            listener.endInput();
        }

        for (int i = 0; i < queueSize; i++) {
            InputEvent event = inputQueue.get(i);
            if (event.isConsumed()) {
                continue;
            }

            
            //TODO: Eventprocessor
            if (event instanceof MouseMotionEvent) {
                onMouseMotionEventQueued((MouseMotionEvent) event);
            } else if (event instanceof KeyInputEvent) {
                //onKeyEventQueued((KeyInputEvent) event);
            } else if (event instanceof MouseButtonEvent) {
                //onMouseButtonEventQueued((MouseButtonEvent) event);
            } else if (event instanceof JoyAxisEvent) {
                //onJoyAxisEventQueued((JoyAxisEvent) event);
            } else if (event instanceof JoyButtonEvent) {
                //onJoyButtonEventQueued((JoyButtonEvent) event);
            } else if (event instanceof TouchEvent) {
                //onTouchEventQueued((TouchEvent) event);
            } else {
                assert false;
            }
            // larynx, 2011.06.10 - flag event as reusable because
            // the android input uses a non-allocating ringbuffer which
            // needs to know when the event is not anymore in inputQueue
            // and therefor can be reused.
            event.setConsumed();
        }

        inputQueue.clear();
    }
}
