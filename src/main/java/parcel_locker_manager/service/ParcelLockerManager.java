package parcel_locker_manager.service;

import parcel_locker_manager.models.Parcel;
import parcel_locker_manager.models.ParcelLocker;
import parcel_locker_manager.models.Size;
import parcel_locker_manager.models.State;

import java.util.*;
import java.util.stream.Collectors;

public class ParcelLockerManager {
    private final List<ParcelLocker> parcelLockers = new ArrayList<>();
    private final List<Parcel> parcels = new ArrayList<>();

    public void addLocker(ParcelLocker parcelLocker) {
        parcelLockers.add(parcelLocker);
        System.out.println("PARCEL LOCKER ADDED");
    }


    public void removeLocker(String id) {
        ParcelLocker lockerToRemove = getLockerById(id);
        if (lockerToRemove != null) {
            parcelLockers.remove(lockerToRemove);
        } else {
            System.out.printf("LOCKER OF ID => %s <= NOT FOUND!", id);
            System.out.println();
        }
    }

    public void displayAllLockers() {
        System.out.println(!parcelLockers.isEmpty() ? parcelLockers : "NO LOCKERS FOUND!");
    }

    public void displayLockersByCityName(String cityName) {
        List<ParcelLocker> lockers = parcelLockers.stream()
                .filter(parcelLocker -> parcelLocker.getAddress().getCity().equalsIgnoreCase(cityName))
                .collect(Collectors.toList());
        System.out.println(!lockers.isEmpty() ? lockers : "NO LOCKERS FOUND!");

    }

    public void updateLocker(String id, String name, String street, String city, String postalCode) {
        ParcelLocker parcelLockerToUpdate = getLockerById(id);
        if (parcelLockerToUpdate == null) {
            System.out.printf("LOCKER OF ID => %s <= NOT FOUND!", id).println();
        } else {
            parcelLockerToUpdate.setName(name);
            parcelLockerToUpdate.getAddress().setStreet(street);
            parcelLockerToUpdate.getAddress().setCity(city);
            parcelLockerToUpdate.getAddress().setPostalCode(postalCode);
            parcelLockers.set(parcelLockers.indexOf(parcelLockerToUpdate), parcelLockerToUpdate);
        }
    }

    public void addParcel(String name, Size size, double weight, String recipient, String sender, String senderLocker, String recipientLocker, State state) {
        ParcelLocker sndrLocker = getLockerById(senderLocker);
        ParcelLocker recLocker = getLockerById(recipientLocker);
        if (sndrLocker != null && recLocker != null) {
            parcels.add(new Parcel(name, size, weight, recipient, sender, sndrLocker, recLocker, state));
        } else {
            System.out.println("LOCKER NOT FOUND!");
        }

    }

    public void removeParcel(String id) {
        UUID uuid = UUID.fromString(id);
        Parcel parcelToRemove = null;
        for (Parcel parcel : parcels) {
            if (uuid.equals(parcel.getId())) {
                parcelToRemove = parcel;
            }
        }
        if (parcelToRemove != null) {
            parcels.remove(parcelToRemove);
            System.out.println("PARCEL REMOVED");
        } else {
            System.out.printf("PARCEL OF ID => %s <= NOT FOUND!", id).println();
        }
    }

    public void displayAllParcelsByLocker(String id) {
        List<Parcel> parcelsToDisplay = parcels.stream().filter(parcel -> id.equals(parcel.getSenderLocker().getId())).collect(Collectors.toList());
        System.out.println(!parcelsToDisplay.isEmpty() ? parcelsToDisplay : "NO PARCELS FOUND!");
    }

    public void updateParcel(String id, String name, Size size, double weight, String recipient, String sender, String senderLocker, String recipientLocker, State state) {
        Parcel parcel = getParcelById(id);
        if (parcel != null) {
            parcel.setName(name);
            parcel.setSize(size);
            parcel.setWeight(weight);
            parcel.setRecipient(recipient);
            parcel.setSender(sender);
            ParcelLocker sndrLocker = getLockerById(senderLocker);
            if (sndrLocker != null) {
                parcel.setSenderLocker(sndrLocker);
            }
            ParcelLocker rcpLocker = getLockerById(senderLocker);
            if (rcpLocker != null) {
                parcel.setRecipientLocker(rcpLocker);
            }
            parcels.set(parcels.indexOf(parcel), parcel);
        } else {
            System.out.println("PARCEL NOT FOUND!");
        }
    }

    public ParcelLocker getLockerById(String id) {
        ParcelLocker locker = null;
        for (ParcelLocker parcelLocker : parcelLockers) {
            if (id.equals(parcelLocker.getId())) {
                locker = parcelLocker;
                break;
            }
        }
        return locker;
    }

    public Parcel getParcelById(String id) {
        UUID uuid = UUID.fromString(id);
        Parcel parcel = null;
        for (Parcel prcl : parcels) {
            if (uuid.equals(prcl.getId())) {
                parcel = prcl;
                break;
            }
        }
        return parcel;
    }
}
