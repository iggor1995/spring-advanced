package beans.services.api;

import beans.models.Event;
import beans.models.User;

public interface DiscountService {

    double getDiscount(User user, Event event);
}
