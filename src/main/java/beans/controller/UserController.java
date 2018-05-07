package beans.controller;

import beans.daos.DaoException;
import beans.models.User;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class UserController {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, @ModelAttribute("model")ModelMap model)  {
        if(null != user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            try {
                userService.register(user);
            } catch (DaoException e) {
                model.addAttribute("error", "User with such email already exists");
                return "register";
            }
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        HttpServletRequest request){
        User user = userService.getUserByEmail(email);
        if(user != null && password.equals(user.getPassword())){
            request.getSession().setAttribute("registeredUser", user);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public String getUsers(@ModelAttribute("model") ModelMap model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("registeredUser");
        return "redirect:/home";
    }
}