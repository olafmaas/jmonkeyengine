package com.jme3.input.inputListener;

import com.jme3.input.ICursorPos;
import com.jme3.input.IEventQueue;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;

public class TouchInputListener extends BaseInputListener{
	
	private ICursorPos cursorPos;
	
	public TouchInputListener(IEventQueue queue, ICursorPos cp)
	{
		super(queue);
		this.cursorPos = cp;
	}
	
	@Override
	public void onEvent(InputEvent event) {
		if(event instanceof TouchEvent)
		{
			TouchEvent evt = (TouchEvent) event;
	        if (!eventsPermitted) {
	            throw new UnsupportedOperationException("MouseInput has raised an event at an illegal time.");
	        }

	        cursorPos.setCursorPosition(evt.getX(), evt.getY());
	        inputQueue.add(event);
		}
	}
	
}

