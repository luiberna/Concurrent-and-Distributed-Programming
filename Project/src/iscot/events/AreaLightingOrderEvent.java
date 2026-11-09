package iscot.events;

import iscot.enums.Areas;

public class AreaLightingOrderEvent extends HighLevelOrderEvent {

    private final boolean active;

    public AreaLightingOrderEvent(Areas area, boolean active) {
        super(area);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isOn() {
        return isActive();
    }

    @Override
    public String toString() {
        return "[DECIDER ILUMINAÇÃO] " + super.toString() + " | ATIVO: " + isActive();
    }
    
}
