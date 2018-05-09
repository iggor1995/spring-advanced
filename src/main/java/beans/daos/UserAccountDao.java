package beans.daos;

import beans.models.UserAccount;

import java.util.List;

/**
 * Created by Igor_Lapin on 5/9/2018.
 */
public interface UserAccountDao {

    void create(UserAccount userAccount) throws DaoException;

    UserAccount getByUserId(long id);

    void delete(UserAccount userAccount);

    List<UserAccount> getAll();

    void update(UserAccount userAccount);

}
