package com.jme3.input;

public class InputSettings {

    private float frameTPF;
    private float globalAxisDeadZone = 0.05f;
    
	public float getFrameTPF() {
		return frameTPF;
	}
	public void setFrameTPF(float frameTPF) {
		this.frameTPF = frameTPF;
	}
	public float getGlobalAxisDeadZone() {
		return globalAxisDeadZone;
	}
	public void setGlobalAxisDeadZone(float globalAxisDeadZone) {
		this.globalAxisDeadZone = globalAxisDeadZone;
	}
}
