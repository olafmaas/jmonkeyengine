package com.jme3.input;

public class JoystickManager implements IJoyStickSettings{

	private float deadZone = 0.05F;
    private JoyInput joystick;
	
	public JoystickManager(JoyInput joystick)
	{
		this.joystick = joystick;
	}

	@Override
	public void setAxisDeadZone(float deadZone) {
		this.deadZone = deadZone;		
	}

	@Override
	public float getAxisDeadZone() {
		return deadZone;
	}

	@Override
	public Joystick[] getJoysticks() {
		return joystick.loadJoysticks(null);
	}
}
