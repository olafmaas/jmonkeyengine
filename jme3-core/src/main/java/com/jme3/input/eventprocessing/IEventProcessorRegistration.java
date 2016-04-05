package com.jme3.input.eventprocessing;

public interface IEventProcessorRegistration {

	void add(EventProcessor evp);

	void remove(EventProcessor evp);

}