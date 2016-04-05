package com.jme3.input.inputListener;

import com.jme3.input.event.InputEvent;
import com.jme3.input.queue.IEventQueue;

public abstract class BaseInputListener {

	public boolean eventsPermitted = false;
	public IEventQueue inputQueue;
	
	public BaseInputListener(IEventQueue queue)
	{
		this.inputQueue = queue;
	}
	
	
    /**
     * Called before a batch of input will be sent to this
     * <code>RawInputListener</code>.
     */
    public void beginInput(){
    	eventsPermitted = true;
    }

    /**
     * Called after a batch of input was sent to this
     * <code>RawInputListener</code>.
     *
     * The listener should set the {@link InputEvent#setConsumed() consumed flag}
     * on any events that have been consumed either at this call or previous calls.
     */
    public void endInput()
    {
    	eventsPermitted = false;
    }
    
    public abstract void onEvent(InputEvent event);
}
