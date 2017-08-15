package app.base.collector;


public class InvalidCollectConfigurationException extends RuntimeException {
    public InvalidCollectConfigurationException(String message) {
        super(message);
    }

    public InvalidCollectConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCollectConfigurationException(Throwable cause) {
        super(cause);
    }
}
