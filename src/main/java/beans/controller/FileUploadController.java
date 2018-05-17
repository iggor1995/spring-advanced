package beans.controller;

import beans.daos.DaoException;
import beans.models.Event;
import beans.models.User;
import beans.services.ServiceException;
import beans.services.api.EventService;
import beans.services.api.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Igor_Lapin on 4/29/2018.
 */
@Controller
public class FileUploadController {

    @Resource
    @Qualifier("userServiceImpl")
    UserService userService;

    @Resource
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private void parseUsersListToDB(List<User> usersList){
        for(User user : usersList){
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.register(user);
            }
            catch (Exception e) {
                System.out.println("can't create this user, it probably already exists -> " + user);
            }
        }
    }
    @RequestMapping(value = "/usersUpload", method = RequestMethod.POST)
    public String uploadUsersJson(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
                List<User> jsonToPersonList = mapper.readValue(bis, mapType);
                parseUsersListToDB(jsonToPersonList);
                return "redirect:getUsers";
            } catch (Exception e) {
                return "redirect:getUsers";
            }
        } else {
            return "redirect:getUsers";
        }
    }

    @RequestMapping(value = "/eventsUpload", method = RequestMethod.POST)
    public String uploadEventsJson(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                TypeReference<List<Event>> mapType = new TypeReference<List<Event>>() {};
                List<Event> jsonToEventList = mapper.readValue(bis, mapType);
                parseEventsToDb(jsonToEventList);
                return "redirect:home";
            } catch (Exception e) {
                return "redirect:home";
            }
        } else {
            return "redirect:home";
        }
    }

    private void parseEventsToDb(List<Event> events){
        for(Event event : events){
            try {
                eventService.create(event);
            } catch (DaoException e){
                System.out.println("can't create this event, it probably already exists -> " + event);
            }
        }
    }
}
