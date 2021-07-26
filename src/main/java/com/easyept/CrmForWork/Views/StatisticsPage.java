package com.easyept.CrmForWork.Views;

import com.easyept.CrmForWork.Views.UI.SkeletOfApp;
import com.easyept.CrmForWork.service.WorkStatsService;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;

@Route(value = "/stats", layout = SkeletOfApp.class)
public class StatisticsPage extends FlexLayout {

    WorkStatsService statsService;

    Details detailsThisWeek = new Details();
    Details detailsPreviousWeek = new Details();
    Details detailsNextWeek = new Details();

    public StatisticsPage(@Autowired WorkStatsService statsService) {
        this.statsService = statsService;

        add(detailsPreviousWeek);
        add(detailsThisWeek);
        add(detailsNextWeek);

        configDetails(detailsPreviousWeek, "Previous week", Date.valueOf(LocalDate.now().minusDays(7)));
        configDetails(detailsThisWeek, "This week", Date.valueOf(LocalDate.now()));
        configDetails(detailsNextWeek, "Next week", Date.valueOf(LocalDate.now().plusDays(7)));

        setMaxWidth("90%");
        setAlignItems(Alignment.END);
        setFlexWrap(FlexLayout.FlexWrap.WRAP);
        setAlignContent(FlexLayout.ContentAlignment.CENTER);
        setJustifyContentMode(JustifyContentMode.AROUND);
    }

    private void configDetails(Details details, String weekName, Date anyDateInWeek) {
        details.setSummary(new H3("" + weekName));
        int howManyPeopleWorkOnThisWeak = statsService.howManyPeopleWorkOnThisWeek(anyDateInWeek);
        int howManyWorkHours = statsService.howManyWorkDaysOnThisWeek(anyDateInWeek);
        details.addContent(new VerticalLayout(
                new Span("People count: " + howManyPeopleWorkOnThisWeak),
                new Span("Total Work hours: " + howManyWorkHours)
        ));

        details.setOpened(true);
        details.setEnabled(false);
    }



}
