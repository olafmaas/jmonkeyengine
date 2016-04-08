package com.jme3.input.eventprocessing;

import java.util.List;

import com.jme3.input.event.InputEvent;

public interface IEventProcessor {

	void emit(List<InputEvent> inputQueue);
	
	void add(EventProcessor evp);

	void remove(EventProcessor evp);

}