package by.laziuk.controllers;

import by.laziuk.cars.impl.Minivan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@VehicleType(name = "minivan", clazz = Minivan.class,
        path = "/Users/illia/projects/cars/")
public class MinivanController extends VehicleController<Minivan> {
    public MinivanController() {
        super();
    }

    @Override
    @RequestMapping(path="/minivans", method = RequestMethod.GET)
    public String doGetAll(Optional<String> param, Optional<String> id, ModelMap model) {
        List<Minivan> res = getService().get(param.orElse(null));
        model.addAttribute("minivans", res);
        return "minivans";
    }

    @Override
    @RequestMapping(path="/minivan", method = RequestMethod.GET)
    public String doGet(Optional<String> id, ModelMap model) {
        model.addAttribute("minivan", getService().getOne(id.orElse(null)).orElse(new Minivan()));
        return "minivan";
    }

    @Override
    @RequestMapping(value="/minivan", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("minivan") Minivan elem, BindingResult result, ModelMap model) {
        if(getService().getOne(elem.getId()).isPresent()) getService().patch(elem);
        else getService().add(elem);
        return "redirect:/minivans";
    }

    @Override
    @RequestMapping(path="/deleteMinivan", method = RequestMethod.GET)
    public String doDelete(String id, ModelMap model) {
        getService().delete(id);
        return "redirect:/minivans";
    }
}
