package com.jme3.input;

import com.jme3.input.controls.JoyAxisTrigger;
import com.jme3.input.controls.JoyButtonTrigger;
import com.jme3.input.event.InputEvent;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;

public class JoyEventProcessor implements EventProcessor {

	@Override
	public void processEvent(InputEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof JoyButtonEvent){
			onJoyButtonEventQueued((JoyButtonEvent) event);
		}else if(event instanceof JoyAxisEvent){
			onJoyAxisEventQueued((JoyAxisEvent) event);
		}
	}

   private void onJoyAxisEventQueued(JoyAxisEvent evt) {
//	        for (int i = 0; i < rawListeners.size(); i++){
//	            rawListeners.get(i).onJoyAxisEvent(evt);
//	        }

        int joyId = evt.getJoyIndex();
        int axis = evt.getAxisIndex();
        float value = evt.getValue();
        float effectiveDeadZone = Math.max(globalAxisDeadZone, evt.getAxis().getDeadZone()); 
        if (value < effectiveDeadZone && value > -effectiveDeadZone) {
            int hash1 = JoyAxisTrigger.joyAxisHash(joyId, axis, true);
            int hash2 = JoyAxisTrigger.joyAxisHash(joyId, axis, false);

            Float val1 = axisValues.get(hash1);
            Float val2 = axisValues.get(hash2);

            if (val1 != null && val1 > effectiveDeadZone) {
                invokeActions(hash1, false);
            }
            if (val2 != null && val2 > effectiveDeadZone) {
                invokeActions(hash2, false);
            }

            axisValues.remove(hash1);
            axisValues.remove(hash2);

        } else if (value < 0) {
            int hash = JoyAxisTrigger.joyAxisHash(joyId, axis, true);
            int otherHash = JoyAxisTrigger.joyAxisHash(joyId, axis, false);

            // Clear the reverse direction's actions in case we
            // crossed center too quickly
            Float otherVal = axisValues.get(otherHash);
            if (otherVal != null && otherVal > effectiveDeadZone) {
                invokeActions(otherHash, false);
            }

            invokeAnalogsAndActions(hash, -value, effectiveDeadZone, true);
            axisValues.put(hash, -value);
            axisValues.remove(otherHash);
        } else {
            int hash = JoyAxisTrigger.joyAxisHash(joyId, axis, false);
            int otherHash = JoyAxisTrigger.joyAxisHash(joyId, axis, true);

            // Clear the reverse direction's actions in case we
            // crossed center too quickly
            Float otherVal = axisValues.get(otherHash);
            if (otherVal != null && otherVal > effectiveDeadZone) {
                invokeActions(otherHash, false);
            }

            invokeAnalogsAndActions(hash, value, effectiveDeadZone, true);
            axisValues.put(hash, value);
            axisValues.remove(otherHash);
        }
    }
	   
    private void onJoyButtonEventQueued(JoyButtonEvent evt) {
//	        for (int i = 0; i < rawListeners.size(); i++){
//	            rawListeners.get(i).onJoyButtonEvent(evt);
//	        }

        int hash = JoyButtonTrigger.joyButtonHash(evt.getJoyIndex(), evt.getButtonIndex());
        invokeActions(hash, evt.isPressed());
        invokeTimedActions(hash, evt.getTime(), evt.isPressed());
    }   
	
	
	@Override
	public boolean accepts(InputEvent event) {
		if(event instanceof JoyButtonEvent){
			return true;
		}else if(event instanceof JoyAxisEvent){
			return true;
		}
		
		return false;
	}

}
