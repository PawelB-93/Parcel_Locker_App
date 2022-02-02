package parcel_locker_manager.gui;

import parcel_locker_manager.service.TerminalCommands;

import java.util.Scanner;

public class Terminal {
    private final Scanner scanner = new Scanner(System.in);
    TerminalCommands terminalCommands = new TerminalCommands();

    public void initManager() {
        getCommands();
        while (true) {
            switch (scanner.nextLine()) {
                case "1": {
                    terminalCommands.addParcelLocker();
                    getCommands();
                    break;
                }

                case "2": {
                    terminalCommands.removeParcelLocker();
                    getCommands();
                    break;
                }
                case "3": {
                    terminalCommands.displayAllLockers();
                    getCommands();
                    break;
                }

                case "4": {
                    terminalCommands.displayLockersByCityName();
                    getCommands();
                    break;
                }

                case "5": {
                    terminalCommands.updateParcelLocker();
                    getCommands();
                    break;
                }

                case "6": {
                    terminalCommands.addParcel();
                    getCommands();
                    break;
                }

                case "7": {
                    terminalCommands.removeParcel();
                    getCommands();
                    break;
                }

                case "8": {
                    terminalCommands.displayAllParcelsByLocker();
                    getCommands();
                    break;
                }

                case "9": {
                    terminalCommands.updateParcel();
                    getCommands();
                    break;
                }

                case "10": {
                    System.out.println("CLOSING PROGRAM...");
                    return;
                }
            }
        }
    }

    void getCommands() {
        System.out.println("\n###########################\n" +
                "## PARCEL LOCKER MANAGER ##\n" +
                "###########################\n" +
                "     SELECT OPERATION:\n" +
                "\n[1]. Add Parcel Locker\n" +
                "[2]. REMOVE PARCEL LOCKER\n" +
                "[3]. DISPLAY ALL PARCEL LOCKERS\n" +
                "[4]. DISPLAY PARCEL LOCKER BY CITY NAME\n" +
                "[5]. UPDATE PARCEL LOCKER\n" +
                "[6]. ADD PARCEL\n" +
                "[7]. REMOVE PARCEL\n" +
                "[8]. DISPLAY PARCELS BY PARCEL LOCKER\n" +
                "[9]. UPDATE PARCEL\n" +
                "[10]. EXIT PROGRAM");
    }
}
