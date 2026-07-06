package com.vatsal.monitoring.engine;

import com.vatsal.monitoring.enums.ServerStatus;
import com.vatsal.monitoring.exception.DuplicateServerException;
import com.vatsal.monitoring.exception.InvalidIPAddressException;
import com.vatsal.monitoring.exception.ServerNotFoundException;
import com.vatsal.monitoring.manager.AlertManager;
import com.vatsal.monitoring.manager.ServerManager;
import com.vatsal.monitoring.model.Server;
import com.vatsal.monitoring.util.IPAddressValidator;

import java.util.Scanner;

public class MonitoringEngine {

    private final Scanner scanner;
    private final ServerManager serverManager;
    private final AlertManager alertManager;
    private boolean isRunning;

    public MonitoringEngine() {
        scanner = new Scanner(System.in);
        serverManager = new ServerManager();
        alertManager = new AlertManager();
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
                    updateServerStatusById();
                    break;

                case 4:
                    displayAlerts();
                    break;

                case 5:
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
        System.out.println("        Monitoring System");
        System.out.println("=================================");
    }

    private void showMenu() {

        System.out.println("\nMenu");
        System.out.println("1. Add Server");
        System.out.println("2. Display Servers");
        System.out.println("3. Update Server Status");
        System.out.println("4. Display Alert History");
        System.out.println("5. Exit");
    }

    private void addServer() {

        System.out.print("Enter Server ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Server Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter IP Address : ");
        String ipAddress = scanner.nextLine();

        System.out.print("Enter Port : ");
        int port = scanner.nextInt();

        ServerStatus status = chooseStatus();

        try {

            if (!IPAddressValidator.isValid(ipAddress)) {
                throw new InvalidIPAddressException("Invalid IP Address.");
            }

            Server server = new Server(id, name, ipAddress, port, status);

            serverManager.addServer(server);

            System.out.println("Server Added Successfully!");

        } catch (InvalidIPAddressException | DuplicateServerException e) {

            System.out.println(e.getMessage());

        }
    }

    private void updateServerStatusById() {

        System.out.print("Enter Server ID : ");

        int id = scanner.nextInt();

        scanner.nextLine();

        try {
            System.out.println("Select the New Server Status");

            ServerStatus status = chooseStatus();

            Server updatedServer = serverManager.updateStatus(id, status);

            if (updatedServer == null) {

                System.out.println("Server is already in the selected status.");

                return;
            }

            alertManager.createAlert(updatedServer);

            System.out.println("Server Status Updated Successfully!");

        } catch (ServerNotFoundException e) {

            System.out.println(e.getMessage());

        }
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

    private void displayAlerts() {

        System.out.println("\n========== ALERT HISTORY ==========");

        alertManager.displayAlerts();
    }

    private void exitApplication() {

        isRunning = false;

        System.out.println("\nThank you for using ObserveOps Lite.");
    }
}