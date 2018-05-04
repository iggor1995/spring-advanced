package beans.controller;

import beans.models.Event;
import beans.services.api.AuditoriumService;
import beans.services.api.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Igor_Lapin on 4/27/2018.
 */
@Controller
public class MainController {

    @Resource
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String initHomePage(@ModelAttribute("model") ModelMap model){
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return "home";
    }

    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String showRegisterPage(){
        return "register";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String showLoginPage(){
        return "login";
    }
}
