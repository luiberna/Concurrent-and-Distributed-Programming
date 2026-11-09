package iscot.events;

import java.util.Objects;
import iscot.enums.Areas;
import iscot.enums.Type;

public final class RegistrationEvent extends Event {

    private final Type clientType;

    public RegistrationEvent(Areas area, Type clientType) {
        super(area);
        this.clientType = Objects.requireNonNull(clientType);
    }

    public final Type getClientType() {
        return clientType;
    }

    @Override
    public final Type getType() {
        return clientType;
    }

    @Override
    public String toString() {
        return "[REGISTRO] " + super.toString() + " | TIPO DE CLIENTE: " + getClientType();
    }
    
}
