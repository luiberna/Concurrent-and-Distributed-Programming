package iscot.events;

import iscot.enums.Areas;

public class AreaVentilationOrderEvent extends HighLevelOrderEvents {

    //final because the order is immutable, once created it cannot be changed
    private final boolean active;

    public AreaVentilationOrderEvent(Areas area, boolean active) {
        super(area);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "[DECIDER VENTILAÇÃO] " + super.toString() + " | ATIVO: " + isActive();
    }
    
}
