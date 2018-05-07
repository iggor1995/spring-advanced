package beans.controller;

/**
 * Created by Igor_Lapin on 4/30/2018.
 */
public class ControllerException extends Exception {
    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }
}
