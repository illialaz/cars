package by.laziuk.controllers;

import by.laziuk.cars.impl.Minivan;
import by.laziuk.cars.impl.SportCar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@VehicleType(name = "sportcar", clazz = SportCar.class,
        path = "/Users/illia/projects/cars/")
public class SportCarControler extends VehicleController<SportCar> {

    public SportCarControler() {
        super();
    }

    @Override
    @RequestMapping(path="/sportcars", method = RequestMethod.GET)
    public String doGetAll(Optional<String> param, Optional<String> id, ModelMap model) {
        List<SportCar> res = getService().get(param.orElse(null));
        model.addAttribute("sportcars", res);
        return "sportcars";
    }

    @Override
    @RequestMapping(path="/sportcar", method = RequestMethod.GET)
    public String doGet(Optional<String> id, ModelMap model) {
        model.addAttribute("sportcar", getService().getOne(id.orElse(null)).orElse(new SportCar()));
        return "sportcar";    }

    @Override
    @RequestMapping(value="/sportcar", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("sportcar") SportCar elem, BindingResult result, ModelMap model) {
        getService().add(elem);
        return "redirect:/sportcars";
    }

    @Override
    @RequestMapping(path="/deleteSportcar", method = RequestMethod.GET)
    public String doDelete(String id, ModelMap model) {
        getService().delete(id);
        return "redirect:/sportcars";
    }
}
