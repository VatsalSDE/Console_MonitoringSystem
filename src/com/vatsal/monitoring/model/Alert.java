package com.vatsal.monitoring.model;

import com.vatsal.monitoring.enums.AlertSeverity;

import java.time.LocalDateTime;

public class Alert {

    private final int alertId;
    private final int serverId;
    private final String serverName;
    private final AlertSeverity severity;
    private final String message;
    private final LocalDateTime timestamp;

    public Alert(int alertId,
                 int serverId,
                 String serverName,
                 AlertSeverity severity,
                 String message,
                 LocalDateTime timestamp) {

        this.alertId = alertId;
        this.serverId = serverId;
        this.serverName = serverName;
        this.severity = severity;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getAlertId() {
        return alertId;
    }

    public int getServerId() {
        return serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public AlertSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId=" + alertId +
                ", serverId=" + serverId +
                ", serverName='" + serverName + '\'' +
                ", severity=" + severity +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}