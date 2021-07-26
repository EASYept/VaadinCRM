package com.easyept.CrmForWork.service;

import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.entity.Person;
import com.easyept.CrmForWork.util.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Person> personList = new ArrayList<>();
        for (BusinessTrip trip:businessTripList) {
            personList.addAll(trip.getPersons());
        }
        long amountOfPeople = personList.stream().distinct().count();
        howManyPeopleWork = (int) amountOfPeople;
        return howManyPeopleWork;
    }

    public int howManyPeopleWorkOnThisWeek(Date anyDayOfWeek) {
        LocalDate monday = UtilClass.getMondayOfThisWeek(anyDayOfWeek.toLocalDate());
        LocalDate sunday = monday.plusDays(6);
        return howManyPeopleWorkBetweenTwoDates(Date.valueOf(monday), Date.valueOf(sunday));
    }

    public int howManyWorkDays(Date startOfWeek, Date endOfWeek) {
        int howManyHours = 0;
        int hoursInWorkDay = 8;
        List<BusinessTrip> businessTripList =
                businessTripService.findActiveTripsBetweenTwoDates(startOfWeek, endOfWeek);
        for (BusinessTrip trip:businessTripList) {
            int peopleCount = trip.getPersons().size(); //People count
            // is starting date of trip on this week or not
            Date startDate =
                    (trip.getDateOfTrip().before(startOfWeek) ? startOfWeek : trip.getDateOfTrip() );
            // is ending date of trip on this week or not
            Date endDate =
                    (trip.getEndOfTrip().after(endOfWeek) ? endOfWeek : trip.getEndOfTrip() );

            List<LocalDate> allDates = //days list
                    UtilClass.allDatesBetweenDates(
                                                startDate.toLocalDate(),
                                                endDate.toLocalDate());
            for (LocalDate date : allDates) {
                if (date.isAfter(startOfWeek.toLocalDate()) || (date.isEqual(startOfWeek.toLocalDate())) ) {
                    howManyHours += (hoursInWorkDay * peopleCount);
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
