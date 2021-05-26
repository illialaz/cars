package by.laziuk.controllers;

import by.laziuk.cars.impl.IVehicle;
import by.laziuk.cars.impl.Identifiable;
import by.laziuk.cars.impl.Minivan;
import by.laziuk.dao.*;
import by.laziuk.services.CarService;
import by.laziuk.services.ICarService;
import by.laziuk.services.MinivanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

public abstract class VehicleController<T extends IVehicle> {

    private ICarService<T> service;

    public VehicleController() {

        VehicleType a = getClass().getAnnotation(VehicleType.class);
        try {
            switch (Config.getInstance().getInputType()) {
                case ICarService.CSV:
                    service = (ICarService<T>) CarService.class.getConstructors()[0].newInstance(a.clazz(), new CsvDao<>((Class<T>) a.clazz(), a.path() + a.name() + "s.csv"));
                    break;
                case ICarService.JSON:
                    service = (ICarService<T>) CarService.class.getConstructors()[0].newInstance(a.clazz(), new JsonDao<>((Class<T>) a.clazz(), a.path() + a.name() + "s.json"));
                    break;
                case ICarService.XML:
                    service = (ICarService<T>) CarService.class.getConstructors()[0].newInstance(a.clazz(), new XMLDao<>((Class<T>) a.clazz(), a.path() + a.name() + "s.xml"));
                    break;
                case ICarService.SQL:
                    service = (ICarService<T>) CarService.class.getConstructors()[0].newInstance(a.clazz(), new SQLDao<>((Class<T>) a.clazz(), "cars"));
                    break;
                case ICarService.NoSQL:
                    service = (ICarService<T>) CarService.class.getConstructors()[0].newInstance(a.clazz(), new NoSQLDao<>((Class<T>) a.clazz(), "cars"));
                    break;
                default:
                    break;
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public ICarService<T> getService() {
        return service;
    }

    public abstract String doGetAll(@RequestParam Optional<String> param, @RequestParam Optional<String> id, ModelMap model);

    public abstract String doGet(@RequestParam Optional<String> id, ModelMap model);


    public abstract String doPost(@Validated T elem,
                                 BindingResult result, ModelMap model);

    public abstract String doDelete(@RequestParam("id") String id, ModelMap model);
}

