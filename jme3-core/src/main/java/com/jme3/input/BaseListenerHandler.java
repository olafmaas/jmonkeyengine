package com.jme3.input;

import java.util.List;

import com.jme3.input.event.InputEvent;
import com.jme3.util.SafeArrayList;
import com.jme3.input.inputListener.BaseInputListener;

public class BaseListenerHandler implements IBaseListenerRegistration, IBaseListenerEmitter {
	
    private final SafeArrayList<BaseInputListener> baseInputListeners = new SafeArrayList<BaseInputListener>(BaseInputListener.class);

    /* (non-Javadoc)
	 * @see com.jme3.input.IBaseListenerHandler#addRawInputListener(com.jme3.input.inputListener.BaseInputListener)
	 */
    @Override
	public void addRawInputListener(BaseInputListener listener) {
        baseInputListeners.add(listener);
    }

    /* (non-Javadoc)
	 * @see com.jme3.input.IBaseListenerHandler#removeRawInputListener(com.jme3.input.RawInputListener)
	 */
    @Override
	public void removeRawInputListener(RawInputListener listener) {
    	baseInputListeners.remove(listener);
    }

    /* (non-Javadoc)
	 * @see com.jme3.input.IBaseListenerHandler#clearRawInputListeners()
	 */
    @Override
	public void clearRawInputListeners() {
    	baseInputListeners.clear();
    }
    
    
    /* (non-Javadoc)
	 * @see com.jme3.input.IBaseListenerEmitter#emit(java.util.List)
	 */
    @Override
	public void emit(List<InputEvent> inputQueue)
    {
    	int queueSize = inputQueue.size();
    	
        for (BaseInputListener listener : baseInputListeners) {
            listener.beginInput();

            for (int j = 0; j < queueSize; j++) {
                InputEvent event = inputQueue.get(j);
                if (event.isConsumed()) {
                    continue;
                }
                
                listener.onEvent(event);
            }

            listener.endInput();
        }
    }
}
