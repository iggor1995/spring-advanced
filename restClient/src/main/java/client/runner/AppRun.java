package client.runner;

import client.model.Ticket;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class AppRun {
    public static void main(String[] args) {
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
        params.put("ticketId", "1");
        String results = restTemplate.getForObject(url, String.class, params);
        System.out.println(results);
        Ticket result = restTemplate.getForObject(url, Ticket.class, params);
        System.out.println(result);
    }
}
