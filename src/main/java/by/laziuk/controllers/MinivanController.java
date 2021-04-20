package by.laziuk.controllers;
import by.laziuk.cars.impl.Minivan;
import by.laziuk.services.MinivanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class MinivanController {
    @RequestMapping(path="/minivans", method = RequestMethod.GET)
    public String doGetMinivans(@RequestParam Optional<String> param, @RequestParam Optional<String> id, ModelMap model) {
        List<Minivan> res = new MinivanService("XML").get(param.orElse("none"));
        model.addAttribute("minivans", res);
        return "minivans";
    }

    @RequestMapping(path="/minivan", method = RequestMethod.GET)
    public String doGetMinivan(@RequestParam Optional<String> id, ModelMap model) {
        model.addAttribute("minivan", new MinivanService("XML").getOne(id.orElse("")));
        return "minivan";
    }

    @RequestMapping(value="/minivan", method = RequestMethod.POST)
    public String doPostMinivans(@Validated @ModelAttribute("minivan") Minivan minivan,
                                 BindingResult result, ModelMap model) {
        new MinivanService("XML").add(minivan);
        return "redirect:/minivans";
    }

    @RequestMapping(path="/deleteMinivan", method = RequestMethod.GET)
    public String doDeleteMinivan(@RequestParam("id") String id, ModelMap model) {
        new MinivanService("XML").delete(id);
        return "redirect:/minivans";
    }
}
