package beans.daos;

/**
 * Created by Igor_Lapin on 4/30/2018.
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}
