package parcel_locker_manager.service;

import parcel_locker_manager.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class ParcelLockerManager {
    private final List<ParcelLocker> parcelLockers = new ArrayList<>();
    private final List<Parcel> parcels = new ArrayList<>();

    public ParcelLocker addLocker(String id, String name, Address address) {
        ParcelLocker parcelLocker = new ParcelLocker(id, name, address);
        boolean isUnique = parcelLockers.stream().map(locker -> id.equals(locker.getId())).findAny().orElse(Boolean.FALSE);
        if (!isUnique) {
            parcelLockers.add(parcelLocker);
            return parcelLocker;
        }
        return null;
    }


    public ParcelLocker removeLocker(String id) {
        ParcelLocker lockerToRemove = getLockerById(id);
        if (lockerToRemove != null) {
            parcelLockers.remove(lockerToRemove);
        }
        return lockerToRemove;
    }

    public List<ParcelLocker> displayAllLockers() {
        return parcelLockers;
    }

    public List<ParcelLocker> displayLockersByCityName(String cityName) {
        return parcelLockers.stream()
                .filter(parcelLocker -> parcelLocker.getAddress().getCity().equalsIgnoreCase(cityName))
                .collect(Collectors.toList());
    }

    public ParcelLocker updateLocker(String id, String name, String street, String city, String postalCode) {
        ParcelLocker parcelLockerToUpdate = getLockerById(id);
        if (parcelLockerToUpdate != null) {
            parcelLockerToUpdate.setName(name);
            parcelLockerToUpdate.getAddress().setStreet(street);
            parcelLockerToUpdate.getAddress().setCity(city);
            parcelLockerToUpdate.getAddress().setPostalCode(postalCode);
            parcelLockers.set(parcelLockers.indexOf(parcelLockerToUpdate), parcelLockerToUpdate);
        }
        return parcelLockerToUpdate;
    }

    public Parcel addParcel(String name, Size size, double weight, String recipient, String sender, String senderLocker, String recipientLocker, State state) {
        Parcel parcel = null;
        ParcelLocker sndrLocker = getLockerById(senderLocker);
        ParcelLocker recLocker = getLockerById(recipientLocker);
        if (sndrLocker != null && recLocker != null) {
            parcel = new Parcel(name, size, weight, recipient, sender, sndrLocker, recLocker, state);
            parcels.add(parcel);
        }
        return parcel;
    }

    public Parcel removeParcel(String id) {
        UUID uuid = UUID.fromString(id);
        Parcel parcelToRemove = null;
        for (Parcel parcel : parcels) {
            if (uuid.equals(parcel.getId())) {
                parcelToRemove = parcel;
                break;
            }
        }
        if (parcelToRemove != null) {
            parcels.remove(parcelToRemove);
        }
        return parcelToRemove;
    }


    public List<Parcel> displayAllParcelsByLocker(String id) {
        return parcels.stream().filter(parcel -> id.equals(parcel.getSenderLocker().getId())).collect(Collectors.toList());
    }

    public Parcel updateParcel(String id, String name, Size size, double weight, String recipient, String sender, String senderLocker, String recipientLocker, State state) {
        Parcel parcel = null;
        ParcelLocker sndrLocker = getLockerById(senderLocker);
        ParcelLocker rcpLocker = getLockerById(recipientLocker);
        if (sndrLocker != null && rcpLocker != null) {
            parcel = getParcelById(id);
            parcel.setSenderLocker(sndrLocker);
            parcel.setRecipientLocker(rcpLocker);
            parcel.setName(name);
            parcel.setSize(size);
            parcel.setWeight(weight);
            parcel.setRecipient(recipient);
            parcel.setSender(sender);
            parcel.setState(state);
            parcels.set(parcels.indexOf(parcel), parcel);
        }
        return parcel;
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

    public List<Parcel> getAllParcelsBySender(String sender) {
        return parcels.stream().filter(parcel -> sender.equals(parcel.getSender())).collect(Collectors.toList());
    }

    public List<Parcel> getAllParcelsByRecipient(String recipient) {
        return parcels.stream().filter(parcel -> recipient.equals(parcel.getRecipient())).collect(Collectors.toList());
    }
}
