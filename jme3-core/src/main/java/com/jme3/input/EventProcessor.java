package com.jme3.input;

import com.jme3.input.event.InputEvent;

public interface EventProcessor {
	
	public void processEvent(InputEvent event);
	
	public boolean accepts(InputEvent event);

}
