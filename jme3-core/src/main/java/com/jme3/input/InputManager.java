/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.input;

import com.jme3.app.Application;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.input.controls.*;
import com.jme3.input.event.*;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.util.IntMap;
import com.jme3.util.IntMap.Entry;
import com.jme3.util.SafeArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code>InputManager</code> is responsible for converting input events
 * received from the Key, Mouse and Joy Input implementations into an
 * abstract, input device independent representation that user code can use.
 * <p>
 * By default an <code>InputManager</code> is included with every Application instance for use
 * in user code to query input, unless the Application is created as headless
 * or with input explicitly disabled.
 * <p>
 * The input manager has two concepts, a {@link Trigger} and a mapping.
 * A trigger represents a specific input trigger, such as a key button,
 * or a mouse axis. A mapping represents a link onto one or several triggers,
 * when the appropriate trigger is activated (e.g. a key is pressed), the
 * mapping will be invoked. Any listeners registered to receive an event
 * from the mapping will have an event raised.
 * <p>
 * There are two types of events that {@link InputListener input listeners}
 * can receive, one is {@link ActionListener#onAction(java.lang.String, boolean, float) action}
 * events and another is {@link AnalogListener#onAnalog(java.lang.String, float, float) analog}
 * events.
 * <p>
 * <code>onAction</code> events are raised when the specific input
 * activates or deactivates. For a digital input such as key press, the <code>onAction()</code>
 * event will be raised with the <code>isPressed</code> argument equal to true,
 * when the key is released, <code>onAction</code> is called again but this time
 * with the <code>isPressed</code> argument set to false.
 * For analog inputs, the <code>onAction</code> method will be called any time
 * the input is non-zero, however an exception to this is for joystick axis inputs,
 * which are only called when the input is above the {@link InputManager#setAxisDeadZone(float) dead zone}.
 * <p>
 * <code>onAnalog</code> events are raised every frame while the input is activated.
 * For digital inputs, every frame that the input is active will cause the
 * <code>onAnalog</code> method to be called, the argument <code>value</code>
 * argument will equal to the frame's time per frame (TPF) value but only
 * for digital inputs. For analog inputs however, the <code>value</code> argument
 * will equal the actual analog value.
 */
public class InputManager implements RawInputListener {

	Mapper mapper;
	CursorManager cm;

    /**
     * Initializes the InputManager.
     *
     * <p>This should only be called internally in {@link Application}.
     *
     * @param mouse
     * @param keys
     * @param joystick
     * @param touch
     * @throws IllegalArgumentException If either mouseInput or keyInput are null.
     */
    public InputManager(Mapper m, CursorManager cm) {
    	this.mapper = m;
    	this.cm = cm;
    }
    
    /**
    
     * Initializes the InputManager.
     *
     * <p>This should only be called internally in {@link Application}.
     *
     * @throws IllegalArgumentException If either mouseInput or keyInput are null.
     
    public InputManager(MouseInput mouse, KeyInput keys, JoyInput joystick, TouchInput touch) {
        if (keys == null || mouse == null) {
            throw new IllegalArgumentException("Mouse or keyboard cannot be null");
        }
        
        EventProcessorHandler processor = new EventProcessorHandler();
        BaseListenerHandler listener = new BaseListenerHandler();
        
        InputSettings settings = new InputSettings();
        Mapper mapping = new Mapper();
        InputTimer timer = new InputTimer();        
        
        ActionInvoker invoker = new ActionInvoker(settings, mapping, timer);
        
        if(mouse != null){
        	processor.add(new MouseEventProcessor(invoker));
        }
        if(keys != null){
        	processor.add(new KeyEventProcessor(invoker));
        }
        if(joystick != null){
        	processor.add(new JoyEventProcessor(invoker));
        }
        if(touch != null){
        	processor.add(new TouchEventProcessor(invoker));
        }      
        
        inputQueue = new EventQueue(listener, processor);
    }
    **/

    public void addListener(InputListener listener, String... mappingNames) {
        mapper.addListener(listener, mappingNames);;
    }
    
    public void removeListener(InputListener listener) {
	    mapper.removeListener(listener);
    }


    public void addMapping(String mappingName, Trigger... triggers) {
    	mapper.addMapping(mappingName, triggers);
    }

    /**
     * Returns true if this InputManager has a mapping registered
     * for the given mappingName.
     *
     * @param mappingName The mapping name to check.
     *
     * @see InputManager#addMapping(java.lang.String, com.jme3.input.controls.Trigger[])
     * @see InputManager#deleteMapping(java.lang.String)
     */
    public boolean hasMapping(String mappingName) {
        return mapper.hasMapping(mappingName);
    }

    public void deleteMapping(String mappingName) {
    	mapper.deleteMapping(mappingName);
    }
    
    public Vector2f getCursorPosition() {
        return cm.getCursorPosition();
    }
    
    public void setCursorVisible(boolean b)
    {
    	cm.setCursorVisible(true);
    }

	@Override
	public void beginInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onJoyAxisEvent(JoyAxisEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onJoyButtonEvent(JoyButtonEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMotionEvent(MouseMotionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseButtonEvent(MouseButtonEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyEvent(KeyInputEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(TouchEvent evt) {
		// TODO Auto-generated method stub
		
	}




}
