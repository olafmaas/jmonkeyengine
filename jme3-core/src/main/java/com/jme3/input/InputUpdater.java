package com.jme3.input;

import java.util.List;

public class InputUpdater {
	
    private InputTimer timer;
    private ActionInvoker ai;
    private IQueueProcessor proc;
    private List<Input> inputDevices;
    private ISetInputSettings settings;
	
    //inpDevices should be initialized
    public InputUpdater(InputTimer ipt, ActionInvoker ai, IQueueProcessor iq, List<Input> inputDevices, ISetInputSettings isi) {
    	timer = ipt;
    	this.ai = ai;
    	proc = iq;
    	this.inputDevices = inputDevices;
    	settings = isi;
    }
	
    /**
     * Updates the <code>InputManager</code>.
     * This will query current input devices and send
     * appropriate events to registered listeners.
     *
     * @param tpf Time per frame value.
     */
    public void update(float tpf) {
        settings.setFrameTPF(tpf);

        
        // Activate safemode if the TPF value is so small
        // that rounding errors are inevitable
        settings.setSafeMode(tpf < 0.015f);

        long currentTime = java.lang.System.currentTimeMillis();
        timer.setFrameDelta(currentTime - timer.getLastUpdateTime());

        for(Input inp : inputDevices)
        {
        	inp.update();
        }
        

        proc.processQueue();
        ai.invokeUpdateActions();

        timer.setLastLastUpdateTime(timer.getLastUpdateTime());
        timer.setLastUpdateTime(currentTime);
    }
}
