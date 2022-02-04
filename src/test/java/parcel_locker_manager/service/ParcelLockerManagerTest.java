package parcel_locker_manager.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parcel_locker_manager.models.Address;
import parcel_locker_manager.models.ParcelLocker;

import static org.junit.jupiter.api.Assertions.*;

class ParcelLockerManagerTest {

    @Test
    public void verifyIfAdded() {
        //given
        ParcelLockerManager parcelLockerManager = new ParcelLockerManager();
        //when
        parcelLockerManager.addLocker(new ParcelLocker("1", "name", new Address("street", "city", "39-100")));
        //then

    }

}