//GERADO POR IA

package iscot.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import iscot.enums.AirConditioningMode;
import iscot.enums.Areas;
import iscot.enums.Type;
import iscot.events.*;

public final class EventTest {

    private EventTest() { }

    public static void main(String[] args) throws Exception {

        Event[] events = {

            new RegistrationEvent(
                Areas.LivingRoom, Type.ACTUATOR
            ),

            new LightSensorEvent(
                Areas.LivingRoom, 320
            ),

            new TemperatureSensorEvent(
                Areas.LivingRoom, 22.5
            ),

            new HumiditySensorEvent(
                Areas.LivingRoom, 65
            ),

            new GroundMoistureSensorEvent(
                Areas.Garden, 24
            ),

            new AreaLightingOrderEvent(
                Areas.LivingRoom, true
            ),

            new AreaVentilationOrderEvent(
                Areas.LivingRoom, true
            ),

            new AreaIrrigationOrderEvent(
                Areas.Garden, true, 10, 1
            ),

            new AreaTemperatureOrderEvent(
                Areas.LivingRoom,
                AirConditioningMode.Frio, 22
            )
        };

        for (Event event : events) {
            check(event.getArea() != null, "Area presente");
            check(event.getTimestamp() != null, "Timestamp presente");

            System.out.println(event);
        }

        check(
            events[1].getType() == Type.SENSOR,
            "Tipo do sensor"
        );

        check(
            events[5].getType() == Type.DECIDER,
            "Tipo da ordem"
        );

        check(
            ((LightSensorEvent) events[1]).getLightSensorValue() == 320,
            "Valor da luminosidade"
        );

        check(
            ((AreaLightingOrderEvent) events[5]).isOn(),
            "Ordem para ligar"
        );

        ByteArrayOutputStream buffer =
            new ByteArrayOutputStream();

        try (ObjectOutputStream output =
                new ObjectOutputStream(buffer)) {

            output.writeObject(events[1]);
        }

        try (ObjectInputStream input =
                new ObjectInputStream(
                    new ByteArrayInputStream(
                        buffer.toByteArray()
                    )
                )) {

            Object copy = input.readObject();

            check(
                copy instanceof LightSensorEvent,
                "Serializacao do evento"
            );
        }

        boolean invalidRejected = false;

        try {
            new AreaIrrigationOrderEvent(
                Areas.Garden, true, 9, -1
            );
        } catch (IllegalArgumentException expected) {
            invalidRejected = true;
        }

        check(
            invalidRejected,
            "Rejeicao de parametros invalidos"
        );

        System.out.println(
            "TESTES OK: eventos, hierarquia, "
            + "serializacao e validacao."
        );
    }

    private static void check(
            boolean condition,
            String message) {

        if (!condition) {
            throw new AssertionError(
                "Falhou: " + message
            );
        }
    }
}
