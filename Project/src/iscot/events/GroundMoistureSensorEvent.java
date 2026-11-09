package iscot.events;

import iscot.enums.Areas;

public final class GroundMoistureSensorEvent extends SensorEvent {

    private static final long serialVersionUID = 1L;

    public GroundMoistureSensorEvent(Areas area, double value) {
        super(area, value);
    }

    public double getGroundMoistureSensorValue() {
        return getValue();
    }

    @Override
    public String toString() {
        return "[SENSOR HUMIDADE SOLO] " + super.toString() + " | HUMIDADE SOLO: " + getGroundMoistureSensorValue() + " %";
    }
    
}
