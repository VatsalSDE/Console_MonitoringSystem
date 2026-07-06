package com.vatsal.monitoring.manager;

import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.model.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerManager {
    private List<Server> servers = new ArrayList<>();

    public void addServer(Server server){
        servers.add(server);
    }

    public void displayServers(){
        for (Server server : servers){
            System.out.println(server);  // here the to string method that was made works likewise
        }
    }


    // for finding the server by the server id
    public Server findServerById(int id) {
        for (Server sr : servers) {
            if (sr.getId() == id) {
                return sr;
            }
        }
        return null;
    }

    // for setting the server status
    public void updateStatus(int serverId , ServerStatus str){
        for (Server sr : servers){
            if(sr.getId() == serverId){
                sr.setStatus(str);
            }
        }
    }

}