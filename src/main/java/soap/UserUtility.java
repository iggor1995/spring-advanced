package soap;

import beans.models.User;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Igor_Lapin on 5/18/2018.
 */
@Component
@ComponentScan("beans")
public class UserUtility {

    @Resource
    @Qualifier("userServiseImpl")
    UserService userService;

    public com.springadv.soap.User getUser(long id){
        User user = userService.getById(id);
        com.springadv.soap.User endUser = new com.springadv.soap.User();
        endUser.setName(user.getName());
        endUser.setUserId(user.getId());
        return endUser;
    }
}
