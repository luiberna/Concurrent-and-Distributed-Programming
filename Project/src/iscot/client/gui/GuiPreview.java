package iscot.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import iscot.enums.AirConditioningMode;

public class GuiPreview {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JPanel components = new JPanel(new GridLayout(2, 3, 12, 12));
            components.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
            components.setPreferredSize(new Dimension(900, 500));

            components.add(togglePanel("Light", new LightIcon()));
            components.add(togglePanel("Ventilator", new VentilatorIcon()));
            components.add(airConditioningPanel());
            components.add(togglePanel("Irrigation", new IrrigationIcon()));
            components.add(new SensorControl(
                "", "Humidity", "%", 0, 100, 1, 50,
                value -> System.out.println("Humidity: " + value + " %")
            ));
            components.add(new SensorControl(
                "", "Temperature", "°C", 0, 40, 1, 20,
                value -> System.out.println("Temperature: " + value + " °C")
            ));

            JFrame frame = new JFrame("IscoT - GUI preview");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(components);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JPanel iconPanel(String title, ComponentState icon) {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(icon, BorderLayout.CENTER);
        return panel;
    }

    private static JPanel togglePanel(String title, ComponentState icon) {
        JPanel panel = iconPanel(title, icon);
        JCheckBox enabled = new JCheckBox("On", icon.isAtivo());
        enabled.addActionListener(event -> icon.setAtivo(enabled.isSelected()));
        panel.add(enabled, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel airConditioningPanel() {
        ACIcon icon = new ACIcon();
        icon.setTemperatura(22);
        JPanel panel = iconPanel("Air conditioning", icon);

        JComboBox<AirConditioningMode> mode = new JComboBox<>(AirConditioningMode.values());
        mode.setSelectedItem(icon.getModo());
        mode.addActionListener(event -> icon.setModo((AirConditioningMode) mode.getSelectedItem()));

        JSpinner temperature = new JSpinner(new SpinnerNumberModel(22, 16, 30, 1));
        temperature.addChangeListener(event ->
            icon.setTemperatura(((Number) temperature.getValue()).doubleValue())
        );

        JPanel controls = new JPanel(new GridLayout(2, 2, 4, 4));
        controls.add(new JLabel("Mode"));
        controls.add(mode);
        controls.add(new JLabel("Temperature (°C)"));
        controls.add(temperature);
        panel.add(controls, BorderLayout.SOUTH);
        return panel;
    }
}
