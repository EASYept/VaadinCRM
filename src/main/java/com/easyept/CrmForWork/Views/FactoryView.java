package com.easyept.CrmForWork.Views;


import com.easyept.CrmForWork.Views.UI.SkeletOfApp;
import com.easyept.CrmForWork.entity.Factory;
import com.easyept.CrmForWork.service.FactoryService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value ="/factories", layout = SkeletOfApp.class)
public class FactoryView extends VerticalLayout {

    private GridCrud<Factory> crud = new GridCrud<>(Factory.class);
    private FactoryService factoryService;

    public FactoryView(FactoryService factoryService) {

        crud.setFindAllOperation(factoryService::findAll);
        crud.setAddOperation(factoryService::add);
        crud.setUpdateOperation(factoryService::update);
        crud.setDeleteOperation(factoryService::delete);

        add(crud);
        setSizeFull();

    }

}
