package iscot.events;

import iscot.enums.Areas;
import iscot.enums.Type;

public class SensorEvent extends Event {
    
    private final double value;

    //proteced constructor to ensure that only subclasses can instantiate SensorEvent
    protected SensorEvent(Areas area, double value) {
        super(area);
        this.value = value;
    }

    public final double getValue(){
        return value;
    }

    @Override
    public final Type getType() {
        return Type.SENSOR;
    }
}
