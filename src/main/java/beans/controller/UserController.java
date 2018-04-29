package beans.controller;

import beans.models.User;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class UserController {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, HttpServletRequest request){
        if(null != user){
            userService.register(user);
            request.getSession().setAttribute("registeredUser", user);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, @ModelAttribute("model") ModelMap model){
        User user = userService.getUserByEmail(email);
        if(user != null && password.equals(user.getPassword())){
            model.addAttribute("registeredUser", user);
        }
        ModelAndView mov = new ModelAndView("redirect:/home");
        mov.addObject(model);
//        return "redirect:/home";
        return mov;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public String getUsers(@ModelAttribute("model") ModelMap model, @RequestParam("name") String name){
        List<User> users = userService.getUsersByName(name);
        model.addAttribute("usersByName", users);
        return "redirect:home";
    }
}