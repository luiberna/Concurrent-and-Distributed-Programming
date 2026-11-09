package iscot.events;

import iscot.enums.Areas;

public final class AreaIrrigationOrderEvent extends HighLevelOrderEvent {

    private final boolean active;
    private final int durationMinutes;
    private final int zone;

    public AreaIrrigationOrderEvent(Areas area, boolean active, int durationMinutes, int zone) {
        super(area);

        if (zone < 1 || zone > 3 || durationMinutes < 0 ) {
            throw new IllegalArgumentException("Zone must be between 1 and 3, and duration must be a non-negative integer");
        }

        this.active = active;
        this.durationMinutes = durationMinutes;
        this.zone = zone;
    }

    public boolean isActive() {
        return active;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getZone() {
        return zone;
    }

    @Override
    public String toString() {
        return "[DECIDER IRRIGAÇÃO] " + super.toString() + " | ATIVO: " + isActive() + 
        " | DURAÇÃO: " + getDurationMinutes() + " minutos" + " | ZONA: " + getZone();
    }
}
