package com.jme3.input;

import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;

public class MouseEventProcessor implements EventProcessor {

	@Override
	public void processEvent(InputEvent event) {
		if(event instanceof MouseButtonEvent) {
			onMouseButtonEventQueued((MouseButtonEvent) event);
		} else if(event instanceof MouseMotionEvent) {
			onMouseMotionEventQueued((MouseMotionEvent) event);
		}
	}

    private void onMouseButtonEventQueued(MouseButtonEvent evt) {
        int hash = MouseButtonTrigger.mouseButtonHash(evt.getButtonIndex());
        invokeActions(hash, evt.isPressed());
        invokeTimedActions(hash, evt.getTime(), evt.isPressed());
    }
	
    
    private void onMouseMotionEventQueued(MouseMotionEvent evt) {

      if (evt.getDX() != 0) {
          float val = Math.abs(evt.getDX()) / 1024f;
          invokeAnalogsAndActions(MouseAxisTrigger.mouseAxisHash(MouseInput.AXIS_X, evt.getDX() < 0), val, globalAxisDeadZone, false);
      }
      if (evt.getDY() != 0) {
          float val = Math.abs(evt.getDY()) / 1024f;
          invokeAnalogsAndActions(MouseAxisTrigger.mouseAxisHash(MouseInput.AXIS_Y, evt.getDY() < 0), val, globalAxisDeadZone, false);
      }
      if (evt.getDeltaWheel() != 0) {
          float val = Math.abs(evt.getDeltaWheel()) / 100f;
          invokeAnalogsAndActions(MouseAxisTrigger.mouseAxisHash(MouseInput.AXIS_WHEEL, evt.getDeltaWheel() < 0), val, globalAxisDeadZone, false);
      }
    }
	
	@Override
	public boolean accepts(InputEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof MouseMotionEvent)
		{
			return true;
		}else if(event instanceof MouseButtonEvent){
			return true;
		}
		
		return false;
	}

}
