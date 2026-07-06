package com.vatsal.monitoring.util;

public class IPAddressValidator {

    public static boolean isValid(String ip) {

        if (ip == null || ip.isEmpty()) {
            return false;
        }

        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {

            try {

                int value = Integer.parseInt(part);

                if (value < 0 || value > 255) {
                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}