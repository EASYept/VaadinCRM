package com.easyept.CrmForWork.util;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateUtilClass {

    public static List<LocalDate> allDatesBetweenDates(LocalDate d1, LocalDate d2) {
        List<LocalDate> datesBetween = new ArrayList<>(Math.toIntExact(ChronoUnit.DAYS.between(d1,d2)));
        while (!d1.isAfter(d2)) {
            datesBetween.add(d1);
            d1 = d1.plusDays(1);
        }
        return datesBetween;
    }

    public static LocalDate getMondayOfThisWeek(LocalDate localDate) {
        int d = localDate.getDayOfWeek().getValue();
        switch (d){
            case 1: break;
            case 2: localDate = localDate.minusDays(1); break;
            case 3: localDate = localDate.minusDays(2); break;
            case 4: localDate = localDate.minusDays(3); break;
            case 5: localDate = localDate.minusDays(4); break;
            case 6: localDate = localDate.minusDays(5); break;
            case 7: localDate = localDate.minusDays(6); break;
        }
        return localDate;
    }


}
