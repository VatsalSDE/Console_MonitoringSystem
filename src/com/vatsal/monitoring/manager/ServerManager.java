package com.vatsal.monitoring.manager;

import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.model.Server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerManager {
    // now replacing the list to map

    private Map<Integer,Server> servers = new LinkedHashMap<>();

    public void addServer(Server server){
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
        if(servers.containsKey(id)){
            return servers.get(id);
        }
        return null;
    }

    // for setting the server status
    public void updateStatus(int serverId , ServerStatus str){
        Server server = findServerById(serverId);

        if(server!=null){

            server.setStatus(str);

        }
    }

}