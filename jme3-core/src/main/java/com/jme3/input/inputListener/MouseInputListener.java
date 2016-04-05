package com.jme3.input.inputListener;

import com.jme3.input.ICursorPos;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.queue.IEventQueue;

public class MouseInputListener extends BaseInputListener {

	private ICursorPos cursorPos;
	
	public MouseInputListener(IEventQueue queue, ICursorPos cp)
	{
		super(queue);
		this.cursorPos = cp;
	}
	
	@Override
	public void onEvent(InputEvent event) {
        if (!eventsPermitted) {
            throw new UnsupportedOperationException("MouseInput has raised an event at an illegal time.");
        }
		
		if(event instanceof MouseMotionEvent){
			MouseMotionEvent evt = (MouseMotionEvent) event;
			cursorPos.setCursorPosition(evt.getX(), evt.getY());
		}else if(event instanceof MouseButtonEvent){
			MouseButtonEvent evt = (MouseButtonEvent) event;
			cursorPos.setCursorPosition(evt.getX(), evt.getY());
		}else {
			return;
		}
		
		
		inputQueue.add(event);
	}
	
}
