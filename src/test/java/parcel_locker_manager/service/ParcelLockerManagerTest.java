package parcel_locker_manager.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parcel_locker_manager.models.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ParcelLockerManagerTest {
    ParcelLockerManager parcelLockerManager;

    @BeforeEach
    void initManager() {
        parcelLockerManager = new ParcelLockerManager();
    }

    @Test
    void when_addLocker_is_used_then_locker_should_be_returned() {
        //given
        //when
        ParcelLocker parcelLocker = parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        //then
        assertEquals("AAA11", parcelLocker.getId());
        assertEquals("Locker1", parcelLocker.getName());
        assertEquals("street1", parcelLocker.getAddress().getStreet());
    }

    @Test
    void when_addLocker_is_used_and_id_is_not_unique_then_null_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        //when
        ParcelLocker parcelLocker2 = parcelLockerManager.addLocker("AAA11", "Locker2", new Address("street2", "city2", "50-200"));
        //then
        assertNull(parcelLocker2);

    }

    @Test
    void when_removeLocker_is_used_then_locker_to_remove_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        //when
        ParcelLocker parcelLocker = parcelLockerManager.removeLocker("AAA11");
        //then
        assertAll(
                () -> assertEquals("AAA11", parcelLocker.getId()),
                () -> assertEquals("Locker1", parcelLocker.getName()),
                () -> assertEquals("street1", parcelLocker.getAddress().getStreet())
        );
    }

    @Test
    void when_removeLocker_is_used_and_locker_does_not_exist_then_null_should_be_returned() {
        //given
        //when
        ParcelLocker parcelLocker = parcelLockerManager.removeLocker("AAA11");
        //then
        assertNull(parcelLocker);
    }

    @Test
    void when_displayAllLockers_is_used_then_list_of_lockers_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city1", "50-200"));
        //when
        List<ParcelLocker> parcelLockers = parcelLockerManager.displayAllLockers();
        //then
        assertAll(
                () -> assertEquals(parcelLockers.size(), 2),
                () -> assertEquals("AAA11", parcelLockers.get(0).getId()),
                () -> assertEquals("BBB22", parcelLockers.get(1).getId()),
                () -> assertEquals("Locker1", parcelLockers.get(0).getName()),
                () -> assertEquals("Locker2", parcelLockers.get(1).getName())
        );
    }

    @Test
    void when_displayAllLockers_is_used_and_list_of_lockers_is_empty_then_empty_list_should_be_returned() {
        //given
        //when
        List<ParcelLocker> parcelLockers = parcelLockerManager.displayAllLockers();
        //then
        assertEquals(0, parcelLockers.size());

    }

    @Test
    void when_displayLockersByCityName_is_used_then_list_of_lockers_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        parcelLockerManager.addLocker("CCC33", "Locker3", new Address("street3", "city3", "60-300"));
        parcelLockerManager.addLocker("DDD44", "Locker4", new Address("street4", "city1", "70-400"));
        //when
        List<ParcelLocker> parcelLockers = parcelLockerManager.displayLockersByCityName("city3");
        //then
        assertAll(
                () -> assertEquals("BBB22", parcelLockers.get(0).getId()),
                () -> assertEquals("CCC33", parcelLockers.get(1).getId()),
                () -> assertEquals("street2", parcelLockers.get(0).getAddress().getStreet()),
                () -> assertEquals("street3", parcelLockers.get(1).getAddress().getStreet())
        );
    }

    @Test
    void when_displayLockersByCityName_is_used_and_nothing_is_found_then_empty_list_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        parcelLockerManager.addLocker("CCC33", "Locker3", new Address("street3", "city3", "60-300"));
        parcelLockerManager.addLocker("DDD44", "Locker4", new Address("street4", "city1", "70-400"));
        //when
        List<ParcelLocker> parcelLockers = parcelLockerManager.displayLockersByCityName("city5");
        //then
        assertEquals(0, parcelLockers.size());
    }

    @Test
    void when_updateLocker_is_used_then_updated_locker_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        // when
        ParcelLocker updatedLocker = parcelLockerManager.updateLocker("AAA11", "updatedName", "updatedStreet", "updatedCity", "50-200");
        //then
        assertAll(
                () -> assertEquals("updatedName", updatedLocker.getName()),
                () -> assertEquals("updatedStreet", updatedLocker.getAddress().getStreet()),
                () -> assertEquals("updatedCity", updatedLocker.getAddress().getCity()),
                () -> assertEquals("50-200", updatedLocker.getAddress().getPostalCode())
        );
    }

    @Test
    void when_updateLocker_is_used_and_locker_does_not_exist_then_null_should_be_returned() {
        //given
        // when
        ParcelLocker updatedLocker = parcelLockerManager.updateLocker("AAA11", "updatedName", "updatedStreet", "updatedCity", "50-200");
        //then
        assertNull(updatedLocker);
    }

    @Test
    void when_addParcel_is_used_then_parcel_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        //when
        Parcel parcel = parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        //then
        assertAll(
                () -> assertEquals("recipient1", parcel.getRecipient()),
                () -> assertEquals(State.SENT, parcel.getState())
        );
    }

    @Test
    void when_addParcel_is_used_and_one_of_lockers_does_not_exist_then_null_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        //when
        Parcel parcel = parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        //then
        assertNull(parcel);
    }

    @Test
    void when_removeParcel_is_used_then_parcel_to_remove_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        Parcel parcel = parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);

        //when
        Parcel parcelToRemove = parcelLockerManager.removeParcel(parcel.getId().toString());
        //then
        assertAll(
                () -> assertEquals("recipient1", parcelToRemove.getRecipient()),
                () -> assertEquals(State.SENT, parcelToRemove.getState())
        );
    }

    @Test
    void when_removeParcel_is_used_and_parcel_does_not_exist_then_null_should_be_returned() {
        //given
        //when
        Parcel parcelToRemove = parcelLockerManager.removeParcel(UUID.randomUUID().toString());
        //then
        assertNull(parcelToRemove);
    }

    @Test
    void when_displayAllParcelsByLocker_is_used_then_list_of_parcels_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        parcelLockerManager.addParcel("parcel2", Size.MEDIUM, 50, "recipient2"
                , "sender2", "AAA11", "BBB22", State.ON_THE_WAY);
        parcelLockerManager.addParcel("parcel3", Size.LARGE, 100, "recipient3"
                , "sender3", "BBB22", "AAA11", State.RECEIVED);

        //when
        List<Parcel> parcels = parcelLockerManager.displayAllParcelsByLocker("AAA11");
        //then
        assertAll(
                () -> assertEquals("parcel1", parcels.get(0).getName()),
                () -> assertEquals("parcel2", parcels.get(1).getName()),
                () -> assertEquals(Size.SMALL, parcels.get(0).getSize()),
                () -> assertEquals(Size.MEDIUM, parcels.get(1).getSize())
        );
    }

    @Test
    void when_displayAllParcelsByLocker_is_used_and_nothing_is_found_then_empty_list_should_be_returned() {
        //given
        //when
        List<Parcel> parcels = parcelLockerManager.displayAllParcelsByLocker("AAA11");
        //then
        assertEquals(0, parcels.size());
    }

    @Test
    void when_updateParcel_is_used_then_updated_parcel_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        Parcel parcel = parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        //when
        Parcel updatedParcel = parcelLockerManager.updateParcel(parcel.getId().toString(), "updatedParcel", Size.MEDIUM, 50, "updatedRecipient"
                , "updatedSender", "AAA11", "BBB22", State.RECEIVED);
        //then
        assertAll(
                () -> assertEquals("updatedParcel", updatedParcel.getName()),
                () -> assertEquals("updatedRecipient", updatedParcel.getRecipient()),
                () -> assertEquals("updatedSender", updatedParcel.getSender()),
                () -> assertEquals(State.RECEIVED, updatedParcel.getState())
        );
    }

    @Test
    void when_updateParcel_is_used_and_parcel_does_not_exist_then_null_should_be_returned() {
        //given
        //when
        Parcel updatedParcel = parcelLockerManager.updateParcel(UUID.randomUUID().toString(), "updatedParcel", Size.MEDIUM, 50, "updatedRecipient"
                , "updatedSender", "AAA11", "BBB22", State.RECEIVED);
        //then
        assertNull(updatedParcel);
    }

    @Test
    void when_updateParcel_is_used_and_one_of_lockers_does_not_exist_then_null_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        Parcel parcel = parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        //when
        Parcel updatedParcel = parcelLockerManager.updateParcel(parcel.getId().toString(), "updatedParcel", Size.MEDIUM, 50, "updatedRecipient"
                , "updatedSender", "AAA11", "CCC33", State.RECEIVED);
        //then
        assertNull(updatedParcel);
    }

    @Test
    void when_getLockerById_is_used_then_locker_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        //when
        ParcelLocker parcelLocker = parcelLockerManager.getLockerById("AAA11");
        //then
        assertAll(
                () -> assertEquals("Locker1", parcelLocker.getName()),
                () -> assertEquals("street1", parcelLocker.getAddress().getStreet()),
                () -> assertEquals("city1", parcelLocker.getAddress().getCity())
        );
    }

    @Test
    void when_getLockerById_is_used_and_locker_does_not_exist_then_null_should_be_returned() {
        //given
        //when
        ParcelLocker parcelLocker = parcelLockerManager.getLockerById("AAA11");
        //then
        assertNull(parcelLocker);
    }

    @Test
    void when_getParcelById_is_used_then_parcel_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        Parcel parcel = parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        //when
        Parcel returnedParcel = parcelLockerManager.getParcelById(parcel.getId().toString());
        //then
        assertAll(
                () -> assertEquals("parcel1", returnedParcel.getName()),
                () -> assertEquals(Size.SMALL, returnedParcel.getSize()),
                () -> assertEquals("recipient1", returnedParcel.getRecipient()),
                () -> assertEquals(State.SENT, returnedParcel.getState())
        );
    }

    @Test
    void when_getParcelById_is_used_and_parcel_does_not_exist_then_null_should_be_returned() {
        //given
        //when
        Parcel returnedParcel = parcelLockerManager.getParcelById(UUID.randomUUID().toString());
        //then
        assertNull(returnedParcel);
    }

    @Test
    void when_getAllParcelsBySender_is_used_then_list_of_parcels_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient1"
                , "sender1", "AAA11", "BBB22", State.SENT);
        parcelLockerManager.addParcel("parcel2", Size.MEDIUM, 50, "recipient2"
                , "sender2", "AAA11", "BBB22", State.ON_THE_WAY);
        parcelLockerManager.addParcel("parcel3", Size.LARGE, 100, "recipient3"
                , "sender1", "BBB22", "AAA11", State.RECEIVED);
        //when
        List<Parcel> parcels = parcelLockerManager.getAllParcelsBySender("sender1");
        //then
        assertAll(
                () -> assertEquals("parcel1", parcels.get(0).getName()),
                () -> assertEquals("parcel3", parcels.get(1).getName()),
                () -> assertEquals(Size.SMALL, parcels.get(0).getSize()),
                () -> assertEquals(Size.LARGE, parcels.get(1).getSize())
        );
    }

    @Test
    void when_getAllParcelsBySender_is_used_and_nothing_is_found_then_empty_list_should_be_returned() {
        //given
        //when
        List<Parcel> parcels = parcelLockerManager.getAllParcelsBySender("sender1");
        //then
        assertEquals(0, parcels.size());
    }

    @Test
    void when_getAllParcelsByRecipient_is_used_then_list_of_parcels_should_be_returned() {
        //given
        parcelLockerManager.addLocker("AAA11", "Locker1", new Address("street1", "city1", "40-100"));
        parcelLockerManager.addLocker("BBB22", "Locker2", new Address("street2", "city3", "50-200"));
        parcelLockerManager.addParcel("parcel1", Size.SMALL, 20, "recipient2"
                , "sender1", "AAA11", "BBB22", State.SENT);
        parcelLockerManager.addParcel("parcel2", Size.MEDIUM, 50, "recipient1"
                , "sender2", "AAA11", "BBB22", State.ON_THE_WAY);
        parcelLockerManager.addParcel("parcel3", Size.LARGE, 100, "recipient1"
                , "sender1", "BBB22", "AAA11", State.RECEIVED);
        //when
        List<Parcel> parcels = parcelLockerManager.getAllParcelsByRecipient("recipient1");
        //then
        assertAll(
                () -> assertEquals("parcel2", parcels.get(0).getName()),
                () -> assertEquals("parcel3", parcels.get(1).getName()),
                () -> assertEquals(Size.MEDIUM, parcels.get(0).getSize()),
                () -> assertEquals(Size.LARGE, parcels.get(1).getSize())
        );
    }

    @Test
    void when_getAllParcelsByRecipient_is_used_and_nothing_is_found_then_empty_list_should_be_returned() {
        //given
        //when
        List<Parcel> parcels = parcelLockerManager.getAllParcelsByRecipient("recipient1");
        //then
        assertEquals(0, parcels.size());
    }

}