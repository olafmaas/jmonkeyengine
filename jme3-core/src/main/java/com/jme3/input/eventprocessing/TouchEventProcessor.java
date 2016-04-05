package com.jme3.input.eventprocessing;

import java.util.ArrayList;

import com.jme3.input.ActionInvoker;
import com.jme3.input.InputManager.Mapping;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.TouchEvent;

public class TouchEventProcessor implements EventProcessor {

	private ActionInvoker invoker;
	
	public TouchEventProcessor(ActionInvoker actionInvoker)
	{
		invoker = actionInvoker;
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