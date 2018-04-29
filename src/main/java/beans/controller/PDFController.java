package beans.controller;

import beans.models.Ticket;
import beans.services.api.BookingService;
import beans.services.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Igor_Lapin on 4/28/2018.
 */
@Controller
public class PDFController {

    @Resource
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String handleAllTradesRequests (@RequestParam("eventName") String event, @RequestParam("auditorium") String auditorium,
                                           @RequestParam("date")LocalDateTime dateTime, Model model) {
        List<Ticket> tickets = bookingService.getTicketsForEvent(event, auditorium, dateTime);
        model.addAttribute("report", tickets);
        return "reportView";
    }
}
