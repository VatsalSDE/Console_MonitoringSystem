package com.vatsal.monitoring.manager;

import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.exception.DuplicateServerException;
import com.vatsal.monitoring.exception.ServerNotFoundException;
import com.vatsal.monitoring.model.Server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerManager {
    // now replacing the list to map

    private Map<Integer,Server> servers = new LinkedHashMap<>();

    public void addServer(Server server){

        if (servers.containsKey(server.getId())) {
            throw new DuplicateServerException(
                    "Server with ID " + server.getId() + " already exists."
            );
        }

        servers.put(server.getId(),server);
    }

    public void displayServers(){

        if(servers.isEmpty()){
            System.out.println("No Servers Added Yet.");

            return;
        }
        for (Server server : servers.values()) {
            System.out.println(server);
        }
    }

    // for finding the server by the server id
    public Server findServerById(int id) {

        Server server = servers.get(id);

        if (server == null) {
            throw new ServerNotFoundException(
                    "Server with ID " + id + " not found."
            );
        }

        return server;
    }

    public Server updateStatus(int serverId, ServerStatus status) {

        Server server = findServerById(serverId);

        if (server.getStatus() == status) {
            return null;
        }

        server.setStatus(status);

        return server;
    }
}