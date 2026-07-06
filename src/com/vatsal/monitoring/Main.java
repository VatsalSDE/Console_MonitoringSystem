package com.vatsal.monitoring;

import com.vatsal.monitoring.engine.MonitoringEngine;
import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.manager.ServerManager;
import com.vatsal.monitoring.model.Server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MonitoringEngine me = new MonitoringEngine();
        me.start();
    }
}