package com.vatsal.monitoring.model;

import com.vatsal.monitoring.enums.ServerStatus;

public class Server {

    private final int id;
    private String name;
    private String ipAddress;
    private int port;
    private ServerStatus status;  // here used the enums for the like better up constants

    public Server(int id, String name, String ipAddress, int port, ServerStatus status) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.port = port;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", status=" + status +
                '}';
    }
}