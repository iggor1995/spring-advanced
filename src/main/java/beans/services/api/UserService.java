package beans.services.api;

import beans.daos.DaoException;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;

import java.util.List;

public interface UserService {

    void updateUserAccount(UserAccount userAccount);

    double getUserCash(User user);

    List<User> getAllUsers();

    User register(User user) throws DaoException;

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<Ticket> getBookedTickets();
}
