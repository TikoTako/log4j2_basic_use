import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.security.GeneralSecurityException;

public class Main {

    /* WARNING UNICODE IN CONSOLE OUTPUT MAY NOT WORK BEFORE WINDOWS 10 */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static void testLogMessages(){
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.error("Error Message!");
        logger.fatal("Fatal Message!");
    }

    private static void generateException() throws Exception {
        throw new Exception("EKSCEPTION");
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(ANSI_GREEN + "HELLO " + ANSI_RESET + ANSI_PURPLE + "POTATO" + ANSI_RESET);

        new Thread(() -> { new Thread(Main::testLogMessages, "Treddy_1b").start(); }, "Treddy_1a").start();

        new Thread(Main::testLogMessages, "Treddy_2").start();

        new Thread( () -> {
            try {
                @SuppressWarnings("NumericOverflow") int explosion = 0/0;
            } catch (Exception e) {
                logger.error(e);
            }
            }, "Treddy_3").start();

        new Thread(() -> {
            try {
                generateException();
            } catch (Exception e) {
                logger.error(e);
            }
        }, "Treddy_4").start();

        try {
            throw new GeneralSecurityException("GeneralSecurityException");
        } catch (GeneralSecurityException e) {
            logger.error(e);
        }

        try {
            throw new OutOfMemoryError("OutOfMemoryError");
        } catch (OutOfMemoryError e) {
            logger.error(e);
        }

        try {
            int error = Integer.getInteger("i'm a string");
        }
        catch (Exception e)
        {
            logger.error("{}, {}! An exception occurred!",
                    "Hello",
                    "World",
                    e);
        }
    }
}
