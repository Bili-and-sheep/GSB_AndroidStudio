package com.example.gsb.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void password_isValid() {
        assertTrue(Utils.isValidPassword("123456"));
    }

    @Test
    public void password_isNotValid() {
        assertFalse(Utils.isValidPassword("123"));
    }
}