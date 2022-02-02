package parcel_locker_manager;

import parcel_locker_manager.gui.Terminal;

public class App {
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.initManager();
    }
}
