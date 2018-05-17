package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.DaoException;
import beans.daos.UserAccountDao;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAccountDao")
public class UserAccountDaoImpl extends AbstractDAO implements UserAccountDao {


    @Override
    public void create(UserAccount userAccount) throws DaoException {
        getCurrentSession().save(userAccount);
    }

    @Override
    public UserAccount getByUserId(long userId) {
        return getCurrentSession().get(UserAccount.class, userId);
    }

    @Override
    public void delete(UserAccount userAccount) {
        getCurrentSession().delete(userAccount);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> getAll() {
        return ((List<UserAccount>) createBlankCriteria(UserAccount.class).list());
    }

    @Override
    public void update(UserAccount userAccount) {
        getCurrentSession().clear();
        getCurrentSession().update(userAccount);
    }
}
