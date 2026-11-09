package iscot.events;

import iscot.enums.Areas;

public final class TemperatureSensorEvent extends SensorEvent {

    private static final long serialVersionUID = 1L;

    public TemperatureSensorEvent(Areas area, double value) {
        super(area, value);
    }

    public double getTemperatureSensorValue() {
        return getValue();
    }

    @Override
    public String toString() {
        return "[SENSOR TEMPERATURA] " + super.toString() + " | TEMPERATURA: " + getTemperatureSensorValue() + " °C";
    }
    
}
