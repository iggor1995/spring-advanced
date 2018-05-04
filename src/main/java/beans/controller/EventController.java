package beans.controller;

import beans.daos.DaoException;
import beans.models.Auditorium;
import beans.models.Event;
import beans.services.api.AuditoriumService;
import beans.services.api.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Igor_Lapin on 4/27/2018.
 */

@Controller
public class EventController {

    @Resource
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Resource
    @Qualifier("auditoriumServiceImpl")
    AuditoriumService auditoriumService;

    @RequestMapping(value = "manager/pageAddEvent", method = RequestMethod.GET)
    public String addEventPage(@ModelAttribute("model") ModelMap model){
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();
        model.addAttribute("auditoriums", auditoriums);
        return "eventAdd";
    }

    @RequestMapping(value = "manager/addEvent", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("event") @Valid Event event, BindingResult result) throws DaoException {
        System.out.println(result);
        if(event != null){
            eventService.create(event);
        }
        return "redirect:/home";
    }
}
