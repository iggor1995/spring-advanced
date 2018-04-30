package beans.services;

/**
 * Created by Igor_Lapin on 4/30/2018.
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
