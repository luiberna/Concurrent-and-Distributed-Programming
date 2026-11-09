package iscot.events;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import iscot.enums.Areas;
import iscot.enums.Type;


public abstract class Event implements Serializable {

    //serialVersionUID is used for version control in a Serializable class.
    //1L means that this is the first version of the class.
    private static final long serialVersionUID = 1L;
    //Area is the location where the event occurred, and timestamp is the time when the event was created.
    private final Areas area;
    private final Instant timestamp;

    protected Event(Areas area) {
        this.area = Objects.requireNonNull(area);
        this.timestamp = Instant.now();
    }

    public final Areas getArea() {
        return area;
    }

    public final Instant getTimestamp() {
        return timestamp;
    }

    public abstract Type getType();
    
}
