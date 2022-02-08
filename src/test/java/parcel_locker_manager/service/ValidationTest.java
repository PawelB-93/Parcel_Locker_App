package parcel_locker_manager.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parcel_locker_manager.models.Size;
import parcel_locker_manager.models.State;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    static Validation validation;

    @BeforeAll
    public static void initValidation() {
        validation = new Validation();
    }

    @Test
    void when_isIdValid_is_true() {
        //given
        //when
        boolean isValid = validation.isIdValid("AAA11");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_isIdValid_is_false() {
        //given
        //when
        boolean isValid = validation.isIdValid("aaa11");
        //then
        assertFalse(isValid);
    }

    @Test
    void when_isNameValid_is_true() {
        //given
        //when
        boolean isValid = validation.isNameValid("Jan");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_isNameValid_is_false() {
        //given
        //when
        boolean isValid = validation.isNameValid("");
        //then
        assertFalse(isValid);
    }

    @Test
    void when_isPostalCodeValid_is_true() {
        //given
        //when
        boolean isValid = validation.isPostalCodeValid("30-100");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_isPostalCodeValid_is_false() {
        //given
        //when
        boolean isValid = validation.isPostalCodeValid("ABC");
        //then
        assertFalse(isValid);
    }

    @Test
    void when_addLockerValidation_is_true() {
        //given
        //when
        boolean isValid = validation.addLockerValidation("AAA11", "name1", "street1", "city1", "30-100");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_addLockerValidation_is_false() {
        //given
        //when
        boolean isValid = validation.addLockerValidation("AAA11", "name1", "street1", "", "30-100");
        //then
        assertFalse(isValid);
    }

    @Test
    void when_updateLockerValidation_is_true() {
        //given
        //when
        boolean isValid = validation.updateLockerValidation("name1", "street1", "city1", "30-100");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_updateLockerValidation_is_false() {
        //given
        //when
        boolean isValid = validation.updateLockerValidation("name1", "", "city1", "30-100");
        //then
        assertFalse(isValid);
    }

    @Test
    void when_addParcelValidation_is_true() {
        //given
        //when
        boolean isValid = validation.addParcelValidation("name1", Size.SMALL, "recipient1", "sender1", State.SENT);
        //then
        assertTrue(isValid);
    }

    @Test
    void when_addParcelValidation_is_false() {
        //given
        //when
        boolean isValid = validation.addParcelValidation("name1", Size.SMALL, "recipient1", "sender1", null);
        //then
        assertFalse(isValid);
    }

    @Test
    void when_updateParcelValidation_is_true() {
        //given
        //when
        boolean isValid = validation.updateParcelValidation("name1", Size.SMALL, 20, "recipient1", "sender1"
                , "senderLocker1", "recipientLocker1", State.SENT);
        //then
        assertTrue(isValid);
    }

    @Test
    void when_updateParcelValidation_is_false() {
        //given
        //when
        boolean isValid = validation.updateParcelValidation("name1", null, 20, "recipient1", "sender1"
                , "senderLocker1", "recipientLocker1", State.SENT);
        //then
        assertFalse(isValid);
    }

    @Test
    void when_isNotBlank_is_true() {
        //given
        //when
        boolean isValid = validation.isNotBlank("abc");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_isNotBlank_is_false() {
        //given
        //when
        boolean isValid = validation.isNotBlank("");
        //then
        assertFalse(isValid);
    }

    @Test
    void when_isNotNull_is_true() {
        //given
        //when
        boolean isValid = validation.isNotNull("abc");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_isNotNull_is_false() {
        //given
        //when
        boolean isValid = validation.isNotNull(null);
        //then
        assertFalse(isValid);
    }


}