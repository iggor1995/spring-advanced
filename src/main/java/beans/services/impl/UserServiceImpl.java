package beans.services.impl;

import beans.daos.DaoException;
import beans.daos.UserDAO;
import beans.models.Ticket;
import beans.models.User;
import beans.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public User register(User user) throws DaoException {
        return userDAO.create(user);
    }

    public void remove(User user) {
        userDAO.delete(user);
    }

    public User getById(long id) {
        return userDAO.get(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    public List<Ticket> getBookedTickets() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
