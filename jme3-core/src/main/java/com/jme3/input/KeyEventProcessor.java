package com.jme3.input;

import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.KeyInputEvent;

public class KeyEventProcessor implements EventProcessor {

	@Override
	public void processEvent(InputEvent event) {
		onKeyEventQueued((KeyInputEvent) event);
	}
	
    private void onKeyEventQueued(KeyInputEvent evt) {
        if (evt.isRepeating()) {
            return; // repeat events not used for bindings
        }

        int hash = KeyTrigger.keyHash(evt.getKeyCode());
        invokeActions(hash, evt.isPressed());
        invokeTimedActions(hash, evt.getTime(), evt.isPressed());
    }

	@Override
	public boolean accepts(InputEvent event) {
		return (event instanceof KeyInputEvent);
	}

}
