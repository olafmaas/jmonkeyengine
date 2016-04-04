package com.jme3.input;

import java.util.ArrayList;

import com.jme3.input.InputManager.Mapping;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.InputListener;
import com.jme3.math.FastMath;
import com.jme3.util.IntMap.Entry;

public class ActionInvoker {

    private void invokeActions(int hash, boolean pressed) {
        ArrayList<Mapping> maps = bindings.get(hash);
        if (maps == null) {
            return;
        }

        int size = maps.size();
        for (int i = size - 1; i >= 0; i--) {
            Mapping mapping = maps.get(i);
            ArrayList<InputListener> listeners = mapping.listeners;
            int listenerSize = listeners.size();
            for (int j = listenerSize - 1; j >= 0; j--) {
                InputListener listener = listeners.get(j);
                if (listener instanceof ActionListener) {
                    ((ActionListener) listener).onAction(mapping.name, pressed, frameTPF);
                }
            }
        }
    }

    private float computeAnalogValue(long timeDelta) {
        if (safeMode || frameDelta == 0) {
            return 1f;
        } else {
            return FastMath.clamp((float) timeDelta / (float) frameDelta, 0, 1);
        }
    }

    private void invokeTimedActions(int hash, long time, boolean pressed) {
        if (!bindings.containsKey(hash)) {
            return;
        }

        if (pressed) {
            pressedButtons.put(hash, time);
        } else {
            Long pressTimeObj = pressedButtons.remove(hash);
            if (pressTimeObj == null) {
                return; // under certain circumstances it can be null, ignore
            }                        // the event then.

            long pressTime = pressTimeObj;
            long lastUpdate = lastLastUpdateTime;
            long releaseTime = time;
            long timeDelta = releaseTime - Math.max(pressTime, lastUpdate);

            if (timeDelta > 0) {
                invokeAnalogs(hash, computeAnalogValue(timeDelta), false);
            }
        }
    }

    private void invokeUpdateActions() {
        for (Entry<Long> pressedButton : pressedButtons) {
            int hash = pressedButton.getKey();

            long pressTime = pressedButton.getValue();
            long timeDelta = lastUpdateTime - Math.max(lastLastUpdateTime, pressTime);

            if (timeDelta > 0) {
                invokeAnalogs(hash, computeAnalogValue(timeDelta), false);
            }
        }

        for (Entry<Float> axisValue : axisValues) {
            int hash = axisValue.getKey();
            float value = axisValue.getValue();
            invokeAnalogs(hash, value * frameTPF, true);
        }
    }

    private void invokeAnalogs(int hash, float value, boolean isAxis) {
        ArrayList<Mapping> maps = bindings.get(hash);
        if (maps == null) {
            return;
        }

        if (!isAxis) {
            value *= frameTPF;
        }

        int size = maps.size();
        for (int i = size - 1; i >= 0; i--) {
            Mapping mapping = maps.get(i);
            ArrayList<InputListener> listeners = mapping.listeners;
            int listenerSize = listeners.size();
            for (int j = listenerSize - 1; j >= 0; j--) {
                InputListener listener = listeners.get(j);
                if (listener instanceof AnalogListener) {
                    // NOTE: multiply by TPF for any button bindings
                    ((AnalogListener) listener).onAnalog(mapping.name, value, frameTPF);
                }
            }
        }
    }

    private void invokeAnalogsAndActions(int hash, float value, float effectiveDeadZone, boolean applyTpf) {
        if (value < effectiveDeadZone) {
            invokeAnalogs(hash, value, !applyTpf);
            return;
        }

        ArrayList<Mapping> maps = bindings.get(hash);
        if (maps == null) {
            return;
        }

        boolean valueChanged = !axisValues.containsKey(hash);
        if (applyTpf) {
            value *= frameTPF;
        }

        int size = maps.size();
        for (int i = size - 1; i >= 0; i--) {
            Mapping mapping = maps.get(i);
            ArrayList<InputListener> listeners = mapping.listeners;
            int listenerSize = listeners.size();
            for (int j = listenerSize - 1; j >= 0; j--) {
                InputListener listener = listeners.get(j);

                if (listener instanceof ActionListener && valueChanged) {
                    ((ActionListener) listener).onAction(mapping.name, true, frameTPF);
                }

                if (listener instanceof AnalogListener) {
                    ((AnalogListener) listener).onAnalog(mapping.name, value, frameTPF);
                }

            }
        }
    }
}
