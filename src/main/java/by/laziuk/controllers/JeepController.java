package by.laziuk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JeepController {
    @RequestMapping(path = "/jeeps", method = RequestMethod.GET)
    public String doGetJeeps(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path = "/jeep", method = RequestMethod.POST)
    public String doPostJeep(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(path = "/jeep", method = RequestMethod.DELETE)
    public String doDeleteJeep(@RequestParam("id") String id, ModelMap model) {
        return "hello";
    }
}
