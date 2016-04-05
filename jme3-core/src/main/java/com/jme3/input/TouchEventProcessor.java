package com.jme3.input;

import java.util.ArrayList;

import com.jme3.input.InputManager.Mapping;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.TouchEvent;

public class TouchEventProcessor implements EventProcessor {

	private ActionInvoker invoker;
	
	public TouchEventProcessor(ActionInvoker ai)
	{
		invoker = ai;
	}
	
	@Override
	public void processEvent(InputEvent event) {
		if(event instanceof TouchEvent)
		{
			invoker.invokeTouchActions((TouchEvent) event);
		}

	}

	@Override
	public boolean accepts(InputEvent event) {
		return event instanceof TouchEvent;
	}

}
