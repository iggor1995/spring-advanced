package beans.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import beans.models.Car;
import beans.models.User;
import beans.services.api.UserService;
import beans.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
@ComponentScan("beans")
public class UserController {

    private static List<Car> carList = new ArrayList<Car>();
    private static List<User> userList = new ArrayList<User>();

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/cars";
    }

    static {
        carList.add(new Car("Honda", "Civic"));
        carList.add(new Car("Toyota", "Camry"));
        carList.add(new Car("Nissan", "Altima"));
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("carList", carList);
        model.addAttribute("userList", userList);
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("car") Car car) {
        if (null != car && null != car.getMake() && null != car.getModel() && !car.getMake().isEmpty() && !car.getModel().isEmpty()) {
            carList.add(car);
        }
        return "redirect:/cars";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user){
        if(null != user){
            userList.add(user);
            if(userService != null) System.out.println(userService);
//          userService.register(user);
        }
        return "redirect:/cars";
    }

}