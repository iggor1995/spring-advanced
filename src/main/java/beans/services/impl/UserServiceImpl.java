package beans.services.impl;

import beans.daos.DaoException;
import beans.daos.UserAccountDao;
import beans.daos.UserDAO;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import beans.services.ServiceException;
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
    private final UserAccountDao userAccountDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO,
                           @Qualifier("userAccountDao")UserAccountDao userAccountDao) {
        this.userDAO = userDAO;
        this.userAccountDAO = userAccountDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public User register(User user) throws ServiceException {
        try {
            User createdUser = userDAO.create(user);
            userAccountDAO.create(new UserAccount(createdUser.getId(), 3000));
            return createdUser;
        } catch (DaoException e) {
            throw new ServiceException("Couldn't store user");
        }
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

    @Override
    public UserAccount getUserAccount(long userId) {
        return userAccountDAO.getByUserId(userId);
    }

    public double getUserCash(User user){
        return userAccountDAO.getByUserId(user.getId()).getCash();
    }

    public void updateUserAccount(UserAccount userAccount){
        userAccountDAO.update(userAccount);
    }
}

