package beans.services.api;

import beans.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Igor_Lapin on 5/4/2018.
 */
public interface UserDetailsService{

     User loadUserEmail(String username) throws UsernameNotFoundException;

}