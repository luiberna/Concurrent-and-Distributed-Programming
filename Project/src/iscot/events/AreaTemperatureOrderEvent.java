package iscot.events;

import java.util.Objects;
import iscot.enums.Areas;
import iscot.enums.AirConditioningMode;

public final class AreaTemperatureOrderEvent extends HighLevelOrderEvent {

    private final AirConditioningMode mode;
    private final double targetTemperature;

    public AreaTemperatureOrderEvent(Areas area, AirConditioningMode mode, double targetTemperature) {
        super(area);
        this.mode = Objects.requireNonNull(mode);

        if (!Double.isFinite(targetTemperature) || targetTemperature < 16 || targetTemperature > 30) {
            throw new IllegalArgumentException("Target temperature must be a finite number between 16 and 30.");
        }

        this.targetTemperature = targetTemperature;
    }

    public final AirConditioningMode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "[DECIDER TEMPERATURA] " + super.toString() + " | MODO: " + getMode() + " | TEMPERATURA: " + targetTemperature + " °C";
    }
    
}
