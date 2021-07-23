package com.easyept.CrmForWork.service;

import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.util.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class WorkStatsService { //TODO should i make only one method "getStatistics" and encapsulate all over?

    private BusinessTripService businessTripService;


    public WorkStatsService(@Autowired BusinessTripService businessTripService) {
        this.businessTripService = businessTripService;
    }

    public int howManyPeopleWorkBetweenTwoDates(Date startOfWeek, Date endOfWeek) {
        int howManyPeopleWork = 0;
        List<BusinessTrip> businessTripList =
                businessTripService.findActiveTripsBetweenTwoDates(startOfWeek, endOfWeek);
        for (BusinessTrip trip:businessTripList) {
            howManyPeopleWork += trip.getPersons().size();
        }
        return howManyPeopleWork;
    }

    public int howManyPeopleWorkOnThisWeek(Date anyDayOfWeek) {
        LocalDate monday = UtilClass.getMondayOfThisWeek(anyDayOfWeek.toLocalDate());
        LocalDate sunday = monday.plusDays(6);
        return howManyPeopleWorkBetweenTwoDates(Date.valueOf(monday), Date.valueOf(sunday));
    }

    public int howManyWorkDays(Date startOfWeek, Date endOfWeek) {
        int howManyHours = 0;
        List<BusinessTrip> businessTripList =
                businessTripService.findActiveTripsBetweenTwoDates(startOfWeek, endOfWeek);
        for (BusinessTrip trip:businessTripList) {
            List<LocalDate> allDates =
                    UtilClass.allDatesBetweenDates(
                                                trip.getDateOfTrip().toLocalDate(),
                                                trip.getEndOfTrip().toLocalDate());
            for (LocalDate date : allDates) {
                if (date.isAfter(startOfWeek.toLocalDate()) || (date.isEqual(startOfWeek.toLocalDate())) ) {
                    howManyHours += 8; //hours
                }
            }
        }
        return howManyHours;
    }

    public int howManyWorkDaysOnThisWeek(Date anyDayOfWeek) {
        LocalDate monday = UtilClass.getMondayOfThisWeek(anyDayOfWeek.toLocalDate());
        LocalDate sunday = monday.plusDays(6);
        return howManyWorkDays(Date.valueOf(monday), Date.valueOf(sunday));
    }


}
