package beans.services.discount;

import beans.daos.DaoException;
import beans.models.User;

public interface DiscountStrategy {

    double calculateDiscount(User user) throws DaoException;
}
