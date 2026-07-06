package com.vatsal.monitoring.manager;

import com.vatsal.monitoring.enums.AlertSeverity;
import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.model.Alert;
import com.vatsal.monitoring.model.Server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertManager {

    private final List<Alert> alerts = new ArrayList<>();

    // Auto Increment Alert ID used here so like the id is like dynamic
    private int nextAlertId = 1;


    public void createAlert(Server server) {

        // No alert is required if server is UP likewise
        if (server.getStatus() == ServerStatus.UP) {
            return;
        }

        AlertSeverity severity;

        switch(server.getStatus()) {

            case DOWN:
                severity = AlertSeverity.CRITICAL;
                break;

            case WARNING:
                severity = AlertSeverity.HIGH;
                break;

            case MAINTENANCE:
                severity = AlertSeverity.MEDIUM;
                break;

            default:
                severity = AlertSeverity.LOW;
        }

        String message = "Server '" + server.getName() +
                "' changed status to " + server.getStatus();

        Alert alert = new Alert(
                nextAlertId++,
                server.getId(),
                server.getName(),
                severity,
                message,
                LocalDateTime.now()
        );

        alerts.add(alert);

        System.out.println("\nAlert Generated Successfully!");
    }

    public void displayAlerts() {

        if (alerts.isEmpty()) {
            System.out.println("No Alerts Generated.");
            return;
        }

        System.out.println("\n========== ALERT HISTORY ==========");

        for (Alert alert : alerts) {
            System.out.println(alert);
        }
    }

    public Alert findAlertById(int alertId) {

        for (Alert alert : alerts) {

            if (alert.getAlertId() == alertId) {
                return alert;
            }
        }

        return null;
    }

    public boolean removeAlert(int alertId) {

        Alert alert = findAlertById(alertId);

        if (alert != null) {
            alerts.remove(alert);
            return true;
        }

        return false;
    }

    public int getTotalAlerts() {
        return alerts.size();
    }

    public List<Alert> getAllAlerts() {
        return alerts;
    }
}