package com.bingo.salute.mq.logger;

import com.bingo.salute.mq.logger.inner.Appender;
import com.bingo.salute.mq.logger.inner.Level;
import com.bingo.salute.mq.logger.inner.Logger;
import com.bingo.salute.mq.logger.inner.LoggingBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InnerLoggerFactoryTest extends BasicLoggerTest {

    private ByteArrayOutputStream byteArrayOutputStream;

    public static final String LOGGER = "ConsoleLogger";

    private PrintStream out;

    @Before
    public void initLogger() {
        out = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Appender consoleAppender = LoggingBuilder.newAppenderBuilder()
                .withConsoleAppender(LoggingBuilder.SYSTEM_OUT)
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        Logger consoleLogger = Logger.getLogger("ConsoleLogger");
        consoleLogger.setAdditivity(false);
        consoleLogger.addAppender(consoleAppender);
        consoleLogger.setLevel(Level.INFO);
    }

    @After
    public void fixConsole() {
        System.setOut(out);
    }

    @Test
    public void testInnerLoggerFactory() {
        InternalLoggerFactory.setCurrentLoggerType(InternalLoggerFactory.LOGGER_INNER);

        InternalLogger logger1 = InnerLoggerFactory.getLogger(LOGGER);
        InternalLogger logger = InternalLoggerFactory.getLogger(LOGGER);

        Assert.assertTrue(logger.getName().equals(logger1.getName()));

        InternalLogger logger2 = InnerLoggerFactory.getLogger(InnerLoggerFactoryTest.class);
        InnerLoggerFactory.InnerLogger logger3 = (InnerLoggerFactory.InnerLogger) logger2;

        logger.info("innerLogger inner info Message");
        logger.error("innerLogger inner error Message", new RuntimeException());
        logger.debug("innerLogger inner debug message");
        logger3.info("innerLogger info message");
        logger3.error("logback error message");
        logger3.info("info {}", "hahahah");
        logger3.warn("warn {}", "hahahah");
        logger3.warn("logger3 warn");
        logger3.error("error {}", "hahahah");
        logger3.debug("debug {}", "hahahah");

        String content = new String(byteArrayOutputStream.toByteArray());
        System.out.println(content);

        Assert.assertTrue(content.contains("InnerLoggerFactoryTest"));
        Assert.assertTrue(content.contains("info"));
        Assert.assertTrue(content.contains("RuntimeException"));
        Assert.assertTrue(!content.contains("debug"));
    }
}
