package iscot.events;

import iscot.enums.Areas;

public final class HumiditySensorEvent extends SensorEvent {

    public HumiditySensorEvent(Areas area, double value) {
        super(area, value);
    }

    public double getHumiditySensorValue() {
        return getValue();
    }

    @Override
    public String toString() {
        return "[SENSOR HUMIDADE] " + super.toString() + " | HUMIDADE: " + getHumiditySensorValue() + " %";
    }
}
