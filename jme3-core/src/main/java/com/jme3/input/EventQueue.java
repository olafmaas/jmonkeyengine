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

public class EventQueue implements IEventQueue, IQueueProcessor{
	
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
    public EventQueue( IBaseListenerEmitter blemitter, IEventProcessorEmitter evpemitter, InputTimer ipt ) {
        
        this.timer = ipt;

        baselisteners = blemitter;
        eventprocessors = evpemitter;
        

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
