package com.jme3.input;

public interface IReadTimer {

	long getLastLastUpdateTime();

	long getLastUpdateTime();

	long getFrameDelta();

}