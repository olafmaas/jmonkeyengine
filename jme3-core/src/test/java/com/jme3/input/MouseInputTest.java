/*
 * Copyright (c) 2009-2015 jMonkeyEngine
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
import com.jme3.input.dummy.*;
import com.jme3.input.event.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests functionality of input behavior as: 
 * 
 * @author 
 */

public class MouseInputTest{
    
	public TestMouseInput mi;
	public KeyInput ki;
	public TouchInput ti;
	public JoyInput ji;
	public InputManager im;
	
	
    @Before
    public void setUp() {
    	ki = new DummyKeyInput();
        ki.initialize();
        
    	mi = new TestMouseInput();    	
        mi.initialize();
    	//mi.setCursorVisible(true);
    	im = new InputManager(mi, ki, null, null);
    }
    
    @Test
    public void testIsSimulateMouse() {
        assertEquals(false, im.isSimulateMouse());   
        /*ti = (TouchInput) new DummyInput();

    	im = new InputManager(mi,ki,null,ti);
        
        assertEquals(true, im.isSimulateMouse()); 
        */
    }
    

    @Test(expected=UnsupportedOperationException.class)
    public void testOnMouseButtonEventNotEventsPermitted() {
    	MouseButtonEvent evt = new MouseButtonEvent(0, true, 5, 10);
        im.onMouseButtonEvent(evt);
    }
    
    @Test
    public void testOnLeftMouseButtonEvent() {
    	mi.addEvent(new MouseButtonEvent(0, true, 5, 10));
    	
    	im.update(1);
    	
        assertEquals(5, im.getCursorPosition().getX(),0.001);
        assertEquals(10, im.getCursorPosition().getY(),0.001);
    }
    
    @Test
    public void testOnRightMouseButtonEvent() {
    	
    	mi.addEvent(new MouseButtonEvent(1, true, 10, 5));
    	
    	im.update(1);
    	
        assertEquals(10, im.getCursorPosition().getX(),0.001);
        assertEquals(5, im.getCursorPosition().getY(),0.001);
    }
}


/*
im.getSimulateMouse();
im.onMouseButtonEvent(evt);
im.onMouseMotionEvent(evt);
im.setSimulateMouse(value);
im.setMouseCursor(jmeCursor);
im.getCursorPosition();
im.setCursorVisible(visible);
*/
