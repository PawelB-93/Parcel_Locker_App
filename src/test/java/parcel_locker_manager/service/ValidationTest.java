package parcel_locker_manager.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void when_isIdValid_is_true() {
        //given
        Validation validation = new Validation();
        //when
        boolean isValid = validation.isIdValid("AAA11");
        //then
        assertTrue(isValid);
    }

    @Test
    void when_isIdValid_is_false() {
        //given
        Validation validation = new Validation();
        //when
        boolean isValid = validation.isIdValid("aaa11");
        //then
        assertFalse(isValid);
    }



}