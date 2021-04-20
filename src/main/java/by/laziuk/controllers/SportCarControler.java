package by.laziuk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportCarControler {
    @RequestMapping(path="/sportcars", method = RequestMethod.GET)
    public String doGetSportCars(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path="/sportcar", method = RequestMethod.POST)
    public String doPostSportCar(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path="/sportcar", method = RequestMethod.DELETE)
    public String doPostJeeps(@RequestParam("id") String id, ModelMap model) {
        model.addAttribute("name");
        return "hello";
    }
}
