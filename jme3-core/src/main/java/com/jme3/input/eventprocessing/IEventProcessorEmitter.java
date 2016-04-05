package com.jme3.input.eventprocessing;

import java.util.List;

import com.jme3.input.event.InputEvent;

public interface IEventProcessorEmitter {

	void emit(List<InputEvent> inputQueue);

}