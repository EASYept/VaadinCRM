package com.easyept.CrmForWork.service;

import com.easyept.CrmForWork.dao.BusinessTripRepository;
import com.easyept.CrmForWork.entity.BusinessTrip;
import com.easyept.CrmForWork.entity.Person;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BusinessTripService {

    private BusinessTripRepository businessTripRepository;

    public BusinessTripService(BusinessTripRepository tripRepository) {
        this.businessTripRepository = tripRepository;
    }

    public List<BusinessTrip> findAll() {
        return businessTripRepository.findAll();
    }

    public BusinessTrip add(BusinessTrip businessTrip) {
        return businessTripRepository.save(businessTrip);
    }

    public BusinessTrip update(BusinessTrip businessTrip) {
        return businessTripRepository.save(businessTrip);
    }

    public void delete(BusinessTrip businessTrip) {
        businessTripRepository.delete(businessTrip);
    }


    public List<BusinessTrip> findAllByText(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return businessTripRepository.findAll();
        }
        return businessTripRepository.search(filterText); //filterText
    }
}
