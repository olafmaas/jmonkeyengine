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

public class EventQueue implements RawInputListener, IEventQueue, IQueueProcessor{
	
    private final KeyInput keys;
    private final MouseInput mouse;
    private final JoyInput joystick;
    private final TouchInput touch;
    private final ArrayList<InputEvent> inputQueue = new ArrayList<InputEvent>();
    private IBaseListenerEmitter baselisteners;
    private IEventProcessorEmitter eventprocessors;
    private InputTimer timer;
    
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
    		IBaseListenerEmitter blemitter, IEventProcessorEmitter evpemitter, InputTimer ipt ) {
        if (keys == null || mouse == null) {
            throw new IllegalArgumentException("Mouse or keyboard cannot be null");
        }

        this.keys = keys;
        this.mouse = mouse;
        this.joystick = joystick;
        this.touch = touch;
        
        this.timer = ipt;

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
    
    

    
    /* (non-Javadoc)
	 * @see com.jme3.input.IQueueProcessor#processQueue()
	 */
    @Override
	public void processQueue() {
        int queueSize = inputQueue.size();
        
        baselisteners.emit(inputQueue);

        eventprocessors.emit(inputQueue);

        inputQueue.clear();
    }


	@Override
	public void add(InputEvent event) {
		 inputQueue.add(event);
	}

}
