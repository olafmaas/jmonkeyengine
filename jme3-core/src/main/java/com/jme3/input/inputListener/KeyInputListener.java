package com.jme3.input.inputListener;

import com.jme3.input.event.InputEvent;
import com.jme3.input.event.KeyInputEvent;

public class KeyInputListener extends BaseInputListener {

	@Override
	public void onEvent(InputEvent event) {
		if(event instanceof KeyInputEvent)
		{
	        if (!eventsPermitted) {
	            throw new UnsupportedOperationException("KeyInput has raised an event at an illegal time.");
	        }

	        inputQueue.add(evt);
		}
	}

	
}
