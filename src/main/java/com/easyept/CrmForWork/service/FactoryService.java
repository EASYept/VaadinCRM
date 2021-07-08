package com.easyept.CrmForWork.service;

import com.easyept.CrmForWork.dao.FactoryRepository;
import com.easyept.CrmForWork.entity.Factory;
import com.easyept.CrmForWork.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class FactoryService {

    private final FactoryRepository factoryRepository;

    @Autowired
    public FactoryService(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    public Iterable<Factory> saveAll(List<Factory> factories) {
        return factoryRepository.saveAll(factories);
    }

    public Collection<Factory> findAll() {
        return factoryRepository.findAll();
    }

    public Factory add(Factory factory) {
        return factoryRepository.save(factory);
    }

    public Factory update(Factory factory) {
        return factoryRepository.save(factory);
    }

    public void delete(Factory factory) {
        factoryRepository.delete(factory);
    }
}
