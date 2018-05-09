package beans.daos.mocks;

import beans.daos.DaoException;
import beans.daos.db.UserAccountDaoImpl;
import beans.models.User;
import beans.models.UserAccount;

import java.util.List;

/**
 * Created by Igor_Lapin on 5/9/2018.
 */
public class UserAccountDaoMock extends UserAccountDaoImpl {

    private final List<User> users;

    public UserAccountDaoMock(List<User> users) {
        this.users = users;
    }

    public void init() {
        cleanup();
        users.forEach((user) -> {
            try {
                create(new UserAccount(user.getId(), 3000));
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });
    }
    public void cleanup() {
        getAll().forEach(this :: delete);
    }

}
