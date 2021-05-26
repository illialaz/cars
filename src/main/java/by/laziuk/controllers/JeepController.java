package by.laziuk.controllers;

import by.laziuk.cars.impl.Jeep;
import by.laziuk.cars.impl.Minivan;
import by.laziuk.services.MinivanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@VehicleType(name = "jeep", clazz = Jeep.class,
        path = "/Users/illia/projects/cars/")
public class JeepController extends VehicleController<Jeep> {

    public JeepController() {
        super();
    }

    @Override
    @RequestMapping(path="/jeeps", method = RequestMethod.GET)
    public String doGetAll(Optional<String> param, Optional<String> id, ModelMap model) {
        List<Jeep> res = getService().get(param.orElse(null));
        model.addAttribute("jeeps", res);
        return "jeeps";
    }

    @Override
    @RequestMapping(path="/jeep", method = RequestMethod.GET)
    public String doGet(Optional<String> id, ModelMap model) {
        model.addAttribute("jeep", getService().getOne(id.orElse(null)).orElse(new Jeep()));
        return "jeep";
    }

    @Override
    @RequestMapping(value="/jeep", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("jeep") Jeep elem, BindingResult result, ModelMap model) {
        getService().add(elem);
        return "redirect:/jeeps";
    }

    @Override
    @RequestMapping(path="/deleteJeep", method = RequestMethod.GET)
    public String doDelete(String id, ModelMap model) {
        getService().delete(id);
        return "redirect:/jeeps";
    }
}
