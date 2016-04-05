package com.jme3.input.inputListener;

import com.jme3.input.event.InputEvent;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;

public class JoyInputListener extends BaseInputListener{

	@Override
	public void onEvent(InputEvent event) {
		if(event instanceof JoyAxisEvent  ||  event instanceof JoyButtonEvent)
		{
	        if (!eventsPermitted) {
	            throw new UnsupportedOperationException("JoyInput has raised an event at an illegal time.");
	        }
	        
	        inputQueue.add(event);
		}
	}
    
}
