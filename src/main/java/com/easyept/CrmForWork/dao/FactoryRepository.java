package com.easyept.CrmForWork.dao;

import com.easyept.CrmForWork.entity.Factory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryRepository extends CrudRepository<Factory, String> {

    List<Factory> findAll();




}
