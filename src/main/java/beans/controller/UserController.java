package beans.controller;

import beans.models.User;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
public class UserController {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user){
        if(null != user){
            userService.register(user);
        }
        return "redirect:/cars";
    }

}