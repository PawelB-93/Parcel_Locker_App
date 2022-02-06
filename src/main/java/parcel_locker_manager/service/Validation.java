package parcel_locker_manager.service;

import parcel_locker_manager.models.ParcelLocker;
import parcel_locker_manager.models.Size;
import parcel_locker_manager.models.State;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public boolean isIdValid(String id) {
        Matcher matcher = Pattern.compile("[A-Z]{3}[0-9]{2}").matcher(id);
        if (matcher.matches()) {
            return true;
        }
        System.out.println("INCORRECT ID FORMAT!");
        return false;
    }

    public boolean isNameValid(String name) {
        if (!name.isBlank()) {
            return true;
        }
        System.out.println("NAME CANNOT BE EMPTY!");
        return false;
    }

    public boolean isPostalCodeValid(String postalCode) {
        Matcher matcher = Pattern.compile("[0-9]{2}-[0-9]{3}").matcher(postalCode);
        if (matcher.matches()) {
            return true;
        }
        System.out.println("INCORRECT POST CODE FORMAT!");
        return false;
    }

    public boolean addLockerValidation(String id, String name, String street, String city, String postalCode) {
        return isIdValid(id) && isNameValid(name) && isNotBlank(street) && isNotBlank(city) && isPostalCodeValid(postalCode);
    }

    public boolean updateLockerValidation(String name, String street, String city, String postalCode) {
        if (isNotBlank(name) && isNameValid(name) && isNotBlank(street) && isNotBlank(city) && isNotBlank(postalCode) && isPostalCodeValid(postalCode)) {
            return true;
        }
        System.out.println("INCORRECT FORMAT OR EMPTY!");
        return false;
    }

    public boolean addParcelValidation(String name, Size size, String recipient, String sender, State state) {
        if (isNotBlank(name) && isNotBlank(recipient) && isNotBlank(sender) && state != null && size != null) {
            return true;
        }
        System.out.println("INCORRECT FORMAT OR EMPTY!");
        return false;
    }

    public boolean updateParcelValidation(String name, Size size, double weight, String recipient, String sender, String senderLocker, String recipientLocker, State state) {
        if (isNotBlank(name) && isNameValid(name) && size != null && isNotBlank(String.valueOf(weight))
                && isNotBlank(recipient) && isNotNull(recipient) && isNotNull(sender)
                && isNotBlank(senderLocker) && state != null) {
            return true;
        }
        System.out.println("INCORRECT FORMAT OR EMPTY!");
        return false;
    }

    boolean isNotBlank(String text) {
        if (text.isBlank()) {
            System.out.println("INCORRECT FORMAT OR EMPTY!");
            return false;
        }
        return true;
    }

    boolean isNotNull(String text) {
        if (text == null) {
            System.out.println("INCORRECT FORMAT OR EMPTY!");
            return false;
        }
        return true;
    }

//    boolean isLockersListEmpty(List<ParcelLocker>parcelLockers){
//        if (parcelLockers.isEmpty()){
//            System.out.println("NO LOCKERS FOUND!");
//            return false;
//        }
//        return true;
//    }

}
