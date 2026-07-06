package com.vatsal.monitoring.engine;

import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.manager.ServerManager;
import com.vatsal.monitoring.model.Server;

import java.util.Scanner;

public class MonitoringEngine {

    private final Scanner scanner;
    private final ServerManager serverManager;
    private boolean isRunning;

    public MonitoringEngine() {
        scanner = new Scanner(System.in);
        serverManager = new ServerManager();
        isRunning = true;
    }

    public void start() {

        printBanner();

        while (isRunning) {

            showMenu();

            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addServer();
                    break;

                case 2:
                    displayServers();
                    break;

                case 3:
                    updateserverstatusbyId();
                    break;
                case 4:
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }

        scanner.close();
    }

    private void printBanner() {
        System.out.println("=================================");
        System.out.println("         Monitoring System");
        System.out.println("=================================");
    }

    private void showMenu() {

        System.out.println("\nMenu");
        System.out.println("1. Add Server");
        System.out.println("2. Display Servers");
        System.out.println("3. Update Server Status");
        System.out.println("4. Exit");
    }

    private void addServer() {

        System.out.print("Enter Server ID : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Server Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter IP Address : ");
        String ipAddress = scanner.nextLine();

        System.out.print("Enter Port : ");
        int port = scanner.nextInt();

        ServerStatus status = chooseStatus();

        Server server = new Server(id, name, ipAddress, port, status);

        serverManager.addServer(server);

        System.out.println("\nServer Added Successfully!");
    }

    private void updateserverstatusbyId(){
        System.out.println("Enter the Server ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Server server =serverManager.findServerById(id);

        if(server == null){
            System.out.println("Server Not Found");

            return;
        }

        System.out.println("Select the server status you want to update");
        ServerStatus status = chooseStatus();

        serverManager.updateStatus(id,status);
        System.out.println("Server Status Updated Successfully");

    }

    private ServerStatus chooseStatus() {

        while (true) {

            System.out.println("\nSelect Server Status");
            System.out.println("1. UP");
            System.out.println("2. DOWN");
            System.out.println("3. WARNING");
            System.out.println("4. MAINTENANCE");
            System.out.print("Enter Choice : ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    return ServerStatus.UP;

                case 2:
                    return ServerStatus.DOWN;

                case 3:
                    return ServerStatus.WARNING;

                case 4:
                    return ServerStatus.MAINTENANCE;

                default:
                    System.out.println("Invalid Status. Try Again.");
            }
        }
    }

    private void displayServers() {

        System.out.println("\n========== SERVER LIST ==========");

        serverManager.displayServers();
    }

    private void exitApplication() {

        isRunning = false;

        System.out.println("\nThank you for using ObserveOps Lite.");
    }
}