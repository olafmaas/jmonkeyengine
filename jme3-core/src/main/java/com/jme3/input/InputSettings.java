package com.jme3.input;

public class InputSettings implements IReadInputSettings {

    private float frameTPF;
    private float globalAxisDeadZone = 0.05f;
    private boolean safemode = false;
    
	/* (non-Javadoc)
	 * @see com.jme3.input.IReadInputSettings#getFrameTPF()
	 */
	@Override
	public float getFrameTPF() {
		return frameTPF;
	}
	public void setFrameTPF(float frameTPF) {
		this.frameTPF = frameTPF;
	}
	/* (non-Javadoc)
	 * @see com.jme3.input.IReadInputSettings#getGlobalAxisDeadZone()
	 */
	@Override
	public float getGlobalAxisDeadZone() {
		return globalAxisDeadZone;
	}
	public void setGlobalAxisDeadZone(float globalAxisDeadZone) {
		this.globalAxisDeadZone = globalAxisDeadZone;
	}
	
	public void setSafeMode(boolean s)
	{
		safemode = s;
	}
	
	public boolean safeModeEnabled()
	{
		return safemode;
	}
	
}
