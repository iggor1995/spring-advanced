package beans.controller;

import beans.models.Ticket;
import beans.services.api.BookingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Resource
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;

    @RequestMapping(value = "/user/report/{ticketId}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> getPurchasedTicket (@PathVariable("ticketId") long ticketId) {
        return new ResponseEntity<>(bookingService.getTicketById(ticketId), HttpStatus.OK);
    }
}
