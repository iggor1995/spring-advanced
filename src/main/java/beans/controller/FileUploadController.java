package beans.controller;

import beans.models.Event;
import beans.models.User;
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
                for(User user : jsonToPersonList){
                    try {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        userService.register(user);
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
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
                List<Event> jsonToPersonList = mapper.readValue(bis, mapType);
                for(Event event : jsonToPersonList){
                    eventService.create(event);
                }
                return "redirect:home";
            } catch (Exception e) {
                return "redirect:home";
            }
        } else {
            return "redirect:home";
        }
    }
}
