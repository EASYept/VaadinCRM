package com.easyept.CrmForWork.dao;

import com.easyept.CrmForWork.entity.BusinessTrip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BusinessTripRepository extends CrudRepository<BusinessTrip, Long> {

    List<BusinessTrip> findAll();

    @Query("select c from BusinessTrip c " +
            "WHERE c.dateOfTrip LIKE CONCAT('%', :filterText, '%') ")
    List<BusinessTrip> search(@Param("filterText") String filter);

    List<BusinessTrip> findAllByEndOfTripGreaterThanEqualAndDateOfTripLessThanEqual(Date start, Date end);

}
