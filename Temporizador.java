/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ducktimer;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author terem
 */
public class Temporizador extends JInternalFrame {

    private final JLabel time;
    private final JLabel status;
    private final JButton start;
    private final JButton reset;
    private final Timer timer;
    private long endTime;

    public Temporizador() {
        setTitle(null);
        setFrameIcon(null);
        setBorder(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        time = new JLabel("00:00:00");
        time.setFont(new Font("Arial", Font.BOLD, 48));
        status = new JLabel("Tiempo restante");
        status.setFont(new Font("Arial", Font.PLAIN, 18));

        start = new JButton("Iniciar");
        start.setBorderPainted(false);
        reset = new JButton("Reiniciar");
        reset.setBorderPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(time, gbc);
        gbc.gridy = 1;
        add(status, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotones.setOpaque(false);
        panelBotones.add(start);
        panelBotones.add(Box.createHorizontalStrut(20));
        panelBotones.add(reset);
        gbc.gridy = 2;
        add(panelBotones, gbc);

        timer = new Timer(200, e -> update());
        start.addActionListener(e -> configure());
        reset.addActionListener(e -> reset());

        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
                timer.stop();
            }
        });

        setVisible(true);
    }

    private void configure() {
        JDialog dialog = new JDialog();
        dialog.setTitle("CONFIGURAR TEMPORIZADOR");
        dialog.setModal(true);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;

        JSpinner hours = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        JSpinner minutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        JSpinner seconds = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        String[] labels = {"Horas:", "Minutos:", "Segundos:"};
        JSpinner[] fields = {hours, minutes, seconds};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i * 2;
            dialog.add(new JLabel(labels[i]), gbc);
            gbc.gridy++;
            dialog.add(fields[i], gbc);
        }

        JButton btn_start = new JButton("Iniciar temporizador");
        gbc.gridy = 6;
        dialog.add(btn_start, gbc);

        btn_start.addActionListener(e -> {
            int hoursValue = (int) hours.getValue();
            int minutesValue = (int) minutes.getValue();
            int secondsValue = (int) seconds.getValue();
            long duration = hoursValue * 3600L + minutesValue * 60L + secondsValue;

            if (duration == 0) {
                JOptionPane.showMessageDialog(dialog,
                    "La duración debe ser mayor que cero.",
                    "Duración no válida",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            endTime = System.currentTimeMillis() + duration * 1000L;
            timer.start();
            update();
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    private void update() {
        long remainingMillis = endTime - System.currentTimeMillis();
        if (remainingMillis <= 0) {
            timer.stop();
            time.setText("00:00:00");
            status.setText("¡Tiempo terminado!");
            JOptionPane.showMessageDialog(this, "¡El temporizador terminó!");
            return;
        }

        long remaining = (remainingMillis + 999) / 1000;
        long hours = remaining / 3600;
        long minutes = (remaining % 3600) / 60;
        long seconds = remaining % 60;
        time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        status.setText("Tiempo restante");
    }

    private void reset() {
        timer.stop();
        endTime = 0;
        time.setText("00:00:00");
        status.setText("Tiempo restante");
    }
}
