package com.jme3.input.queue;

import java.util.ArrayList;

import com.jme3.app.Application;
import com.jme3.input.IBaseListenerEmitter;
import com.jme3.input.event.InputEvent;
import com.jme3.input.eventprocessing.IEventProcessorEmitter;

public class EventQueue implements IEventQueue, IQueueProcessor{
	
    private final ArrayList<InputEvent> inputQueue = new ArrayList<InputEvent>();
    private IBaseListenerEmitter baselisteners;
    private IEventProcessorEmitter eventprocessors;
    
    /**
     * Initializes the InputManager.
     *
     * <p>This should only be called internally in {@link Application}.
     *
     * @throws IllegalArgumentException If either mouseInput or keyInput are null.
     */
    public EventQueue( IBaseListenerEmitter listener, IEventProcessorEmitter eventProcessor ) {
        baselisteners = listener;
        eventprocessors = eventProcessor;
    }
    
    /* (non-Javadoc)
	 * @see com.jme3.input.IQueueProcessor#processQueue()
	 */
    @Override
	public void processQueue() {        
        baselisteners.emit(inputQueue);

        eventprocessors.emit(inputQueue);

        inputQueue.clear();
    }


	@Override
	public void add(InputEvent event) {
		 inputQueue.add(event);
	}

}
