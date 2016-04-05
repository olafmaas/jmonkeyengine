package com.jme3.input;

import java.util.ArrayList;

import com.jme3.app.Application;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.input.IEventProcessorEmitter;

public class EventQueue implements RawInputListener, IEventQueue{
	
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
    private final ArrayList<InputEvent> inputQueue = new ArrayList<InputEvent>();
    private IBaseListenerEmitter baselisteners;
    private IEventProcessorEmitter eventprocessors;
    
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
    public EventQueue(MouseInput mouse, KeyInput keys, JoyInput joystick, TouchInput touch, 
    		IBaseListenerEmitter blemitter, IEventProcessorEmitter evpemitter  ) {
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

        baselisteners = blemitter;
        eventprocessors = evpemitter;
        
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
        
        baselisteners.emit(inputQueue);

        eventprocessors.emit(inputQueue);

        inputQueue.clear();
    }


	@Override
	public void add(InputEvent event) {
		// TODO Auto-generated method stub
		 inputQueue.add(event);
	}

}
