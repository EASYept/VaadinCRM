package com.easyept.CrmForWork.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class UtilClassTest {

    @Test
    void TestGetMondayOfWeek() {
        UtilClass u = new UtilClass();
        LocalDate dateToAssert = u.getMondayOfThisWeek(LocalDate.of(2021, Month.JULY, 20));
        assertEquals(LocalDate.of(2021, Month.JULY, 19), dateToAssert);

    }

}