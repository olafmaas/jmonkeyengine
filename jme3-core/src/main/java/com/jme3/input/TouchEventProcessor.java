package com.jme3.input;

import java.util.ArrayList;

import com.jme3.input.InputManager.Mapping;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.TouchEvent;

public class TouchEventProcessor implements EventProcessor {

	@Override
	public void processEvent(InputEvent event) {
		if(event instanceof TouchEvent)
		{
			onTouchEventQueued((TouchEvent) evt);
		}

	}
	
    /**
     * Dispatches touch events to touch listeners
     * @param evt The touch event to be dispatched to all onTouch listeners
     */
    public void onTouchEventQueued(TouchEvent evt) {
        ArrayList<Mapping> maps = bindings.get(TouchTrigger.touchHash(evt.getKeyCode()));
        if (maps == null) {
            return;
        }

        int size = maps.size();
        for (int i = size - 1; i >= 0; i--) {
            Mapping mapping = maps.get(i);
            ArrayList<InputListener> listeners = mapping.listeners;
            int listenerSize = listeners.size();
            for (int j = listenerSize - 1; j >= 0; j--) {
                InputListener listener = listeners.get(j);
                if (listener instanceof TouchListener) {
                    ((TouchListener) listener).onTouch(mapping.name, evt, frameTPF);
                }
            }
        }
    }

	@Override
	public boolean accepts(InputEvent event) {
		return event instanceof TouchEvent;
	}

}
