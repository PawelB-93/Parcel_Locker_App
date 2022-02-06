package parcel_locker_manager.service;

import parcel_locker_manager.models.*;

import java.util.List;
import java.util.Scanner;

public class TerminalCommands {

    private final Scanner scanner = new Scanner(System.in);
    private final ParcelLockerManager parcelLockerManager = new ParcelLockerManager();
    private final Validation validation = new Validation();

    public void addParcelLocker() {
        System.out.println("ADD ID:");
        String id = scanner.nextLine();
        System.out.println("ADD NAME:");
        String name = scanner.nextLine();
        System.out.println("ADD STREET NAME:");
        String street = scanner.nextLine();
        System.out.println("ADD CITY NAME:");
        String city = scanner.nextLine();
        System.out.println("ADD POSTAL CODE:");
        String postalCode = scanner.nextLine();
        if (validation.addLockerValidation(id, name, street, city, postalCode)) {
            parcelLockerManager.addLocker(id, name, new Address(street, city, postalCode));
            System.out.println("LOCKER ADDED");
        }
    }

    public void removeParcelLocker() {
        System.out.println("ENTER LOCKER ID:");
        String id = scanner.nextLine();
        System.out.println(parcelLockerManager.removeLocker(id) != null ? "LOCKER REMOVED" : "NO LOCKER OF ID => " + id + " <= FOUND!");
    }

    public void displayLockersByCityName() {
        System.out.println("ENTER CITY NAME:");
        String cityName = scanner.nextLine();
        List<ParcelLocker> parcelLockers = parcelLockerManager.displayLockersByCityName(cityName);
        System.out.println(!parcelLockers.isEmpty() ? parcelLockers : "NO LOCKERS FOUND!");
    }

    public void updateParcelLocker() {
        System.out.println("ENTER LOCKER ID:");
        String id = scanner.nextLine();
        System.out.println("UPDATE NAME:");
        String name = scanner.nextLine();
        System.out.println("UPDATE STREET:");
        String street = scanner.nextLine();
        System.out.println("UPDATE CITY:");
        String city = scanner.nextLine();
        System.out.println("UPDATE POSTAL CODE:");
        String postalCode = scanner.nextLine();
        if (validation.updateLockerValidation(name, street, city, postalCode)) {
            System.out.println(parcelLockerManager.updateLocker(id, name, street, city, postalCode) != null ? "LOCKER UPDATED" : "NO LOCKER OF ID => " + id + " <= FOUND!");
        }

    }

    public void addParcel() {
        System.out.println("ADD NAME:");
        String name = scanner.nextLine();
        System.out.println("ADD SIZE:");
        Size size = null;
        System.out.println("1. SMALL || 2. MEDIUM || 3. LARGE");
        switch (scanner.nextLine()) {
            case "1": {
                size = Size.SMALL;
                break;
            }
            case "2": {
                size = Size.MEDIUM;
                break;
            }
            case "3": {
                size = Size.LARGE;
            }
        }
        System.out.println("ADD WEIGHT:");
        double weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("ADD RECIPIENT:");
        String recipient = scanner.nextLine();
        System.out.println("ADD SENDER:");
        String sender = scanner.nextLine();
        System.out.println("ADD SENDER LOCKER:");
        String senderLocker = scanner.nextLine();
        System.out.println("ADD RECIPIENT LOCKER:");
        String recipientLocker = scanner.nextLine();
        System.out.println("CHOOSE STATE:");
        System.out.println("1. SENT || 2. ON THE WAY || 3. RECEIVED");
        State state = null;
        switch (scanner.nextLine()) {
            case "1": {
                state = State.SENT;
                break;
            }
            case "2": {
                state = State.ON_THE_WAY;
                break;
            }
            case "3": {
                state = State.RECEIVED;
            }
        }
        if (validation.addParcelValidation(name, size, recipient, sender, state)) {
            System.out.println(parcelLockerManager.addParcel(name, size, weight, recipient, sender, senderLocker, recipientLocker, state) != null ?
                    "PARCEL ADDED" : "LOCKER NOT FOUND!");
        }

    }

    public void removeParcel() {
        System.out.println("SELECT ID:");
        String id = scanner.nextLine();
        System.out.println(parcelLockerManager.removeParcel(id) != null ? "PARCEL REMOVED" : "PARCEL OF ID => " + id + " <= NOT FOUND!");
    }

    public void displayAllParcelsByLocker() {
        System.out.println("SELECT ID:");
        String id = scanner.nextLine();
        List<Parcel> parcels = parcelLockerManager.displayAllParcelsByLocker(id);
        System.out.println(!parcels.isEmpty() ? parcels : "NO PARCELS FOUND!");

    }

    public void displayAllLockers() {
        List<ParcelLocker> parcelLockers = parcelLockerManager.displayAllLockers();
        System.out.println(!parcelLockers.isEmpty() ? parcelLockers : "NO LOCKERS FOUND!");
    }

    public void updateParcel() {
        System.out.println("SELECT ID:");
        String id = scanner.nextLine();
        System.out.println("SELECT NAME:");
        String name = scanner.nextLine();
        System.out.println("SELECT SIZE:");
        Size size = null;
        System.out.println("1. SMALL || 2. MEDIUM || 3. LARGE");
        switch (scanner.nextLine()) {
            case "1": {
                size = Size.SMALL;
                break;
            }
            case "2": {
                size = Size.MEDIUM;
                break;
            }
            case "3": {
                size = Size.LARGE;
            }
        }
        System.out.println("SELECT WEIGHT:");
        double weight = scanner.nextDouble();
        System.out.println("SELECT RECIPIENT:");
        scanner.nextLine();
        String recipient = scanner.nextLine();
        System.out.println("SELECT SENDER:");
        String sender = scanner.nextLine();
        System.out.println("SELECT SENDER LOCKER:");
        String senderLocker = scanner.nextLine();
        System.out.println("SELECT RECIPIENT LOCKER:");
        String recipientLocker = scanner.nextLine();
        System.out.println("CHOOSE STATE:");
        System.out.println("[1]. SENT || [2]. ON THE WAY || [3]. RECEIVED");
        State state = null;
        switch (scanner.nextLine()) {
            case "1": {
                state = State.SENT;
                break;
            }
            case "2": {
                state = State.ON_THE_WAY;
                break;
            }
            case "3": {
                state = State.RECEIVED;
            }
        }
        if (validation.updateParcelValidation(name, size, weight, recipient, sender, senderLocker, recipientLocker, state)) {
            System.out.println(parcelLockerManager.updateParcel(id, name, size, weight, recipient, sender, senderLocker, recipientLocker, state) != null ?
                    "PARCEL UPDATED" : "NO PARCEL OF ID => " + id + " <= FOUND!");
        }

    }
}
