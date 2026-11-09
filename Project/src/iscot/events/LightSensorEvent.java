package iscot.events;

import iscot.enums.Areas;

public final class LightSensorEvent extends SensorEvent {

    private static final long serialVersionUID = 1L;

    public LightSensorEvent(Areas area, double value) {
        super(area, value);
    }

    public double getLightSensorValue() {
        return getValue();
    }

    @Override
    public String toString() {
        return "[SENSOR LUZ] " + super.toString() + " | LUZ: " + getLightSensorValue() + " lux";
    }
}
