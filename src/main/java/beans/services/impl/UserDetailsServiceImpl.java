package beans.services.impl;

import beans.models.User;
import beans.services.api.UserDetailsService;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Igor_Lapin on 5/4/2018.
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    public User loadUserEmail(String email) throws UsernameNotFoundException {
        return userService.getUserByEmail(email);
    }



}