package beans.models.converter;

import beans.models.User;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;

/**
 * Created by Igor_Lapin on 4/29/2018.
 */
public class StringToUserConverter implements Converter<String, User>{

    @Resource
    @Qualifier("userServiceImpl")
    UserService userService;

    @Override
    public User convert(String userId) {
        return userService.getById(Long.valueOf(userId));
    }
}
