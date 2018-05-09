package beans.services.impl;

import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Lapin on 5/4/2018.
 */
@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    @Qualifier("userServiceImpl")
    private UserService userService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        beans.models.User user;
        user = userService.getUserByEmail(email);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(beans.models.User user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}