package com.bingo.salute.mq.logger;

import com.bingo.salute.mq.logger.inner.Appender;
import com.bingo.salute.mq.logger.inner.Level;
import com.bingo.salute.mq.logger.inner.Logger;
import com.bingo.salute.mq.logger.inner.LoggingBuilder;
import com.bingo.salute.mq.logger.inner.SysLogger;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InternalLoggerTest {

    @Test
    public void testInternalLogger() {
        SysLogger.setQuietMode(false);
        SysLogger.setInternalDebugging(true);
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Appender consoleAppender = LoggingBuilder.newAppenderBuilder()
                .withConsoleAppender(LoggingBuilder.SYSTEM_OUT)
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        Logger consoleLogger = Logger.getLogger("ConsoleLogger");
        consoleLogger.setAdditivity(false);
        consoleLogger.addAppender(consoleAppender);
        consoleLogger.setLevel(Level.INFO);

        Logger.getRootLogger().addAppender(consoleAppender);

        InternalLoggerFactory.setCurrentLoggerType(InternalLoggerFactory.LOGGER_INNER);
        InternalLogger logger = InternalLoggerFactory.getLogger(InternalLoggerTest.class);
        InternalLogger consoleLogger1 = InternalLoggerFactory.getLogger("ConsoleLogger");

        consoleLogger1.warn("simple warn {}", 14555);

        logger.info("testInternalLogger");
        consoleLogger1.info("consoleLogger1");

        System.setOut(out);
        consoleAppender.close();

        String result = new String(byteArrayOutputStream.toByteArray());
        Assert.assertTrue(result.contains("consoleLogger1"));
        Assert.assertTrue(result.contains("testInternalLogger"));
    }

}
