package impl;


/**
 * This class is for logging and debugging purposes.
 *
 * @author Domagoj Trupeljak
 * @author Jacob Yousif
 * @version 1.0
 * @since 2020-09-28
 */
public final class Logger {

    /**
     * The logger.
     */
    private final java.util.logging.Logger mLogger = java.util.logging.Logger.getLogger(getClass().getName());

    /**
     * It logs messages and the time.
     *
     * @param message the message to be printed out.
     */
    public void log(String message) {
        mLogger.info(message);
    }

    /**
     * It messages messages .
     *
     * @param message the message to be printed out.
     */
    public void print(String message) {
        System.out.print(message);
    }

    public void printLine(String message) {
        System.out.println(message);
    }

    public void printLine(String message, boolean isWriting) {
        if (isWriting) printLine(message);
    }
}
