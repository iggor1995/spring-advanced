package client.controller;

import client.model.Ticket;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AppController {

    @RequestMapping(value="/{ticket}", method = RequestMethod.GET)
    public String showTicket(@PathVariable String ticket, Model model, HttpServletRequest request) {
        model.addAttribute("ticket", request.getSession().getAttribute("ticket"));
        request.getSession().removeAttribute("ticket");
        return "ticket";
    }

    @RequestMapping(value="getTicket/{ticketId}", method = RequestMethod.GET)
    public String getTicket(HttpServletRequest request, @PathVariable("ticketId") String ticketId) {
        final String url = "http://localhost:8080/spring_adv/user/report/{ticketId}";

        RestTemplate restTemplate = new RestTemplate();

        // Get the default messageConverterList
        List<HttpMessageConverter<?>> messageConverterList = restTemplate
                .getMessageConverters();

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverterList.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverterList);

        Map<String, String> params = new HashMap<>();
        params.put("ticketId", ticketId);
        Ticket ticket = restTemplate.getForObject(url, Ticket.class, params);

        System.out.println(ticket);
        request.getSession().setAttribute("ticket", ticket);

        return "redirect:/ticket.pdf";
    }
}