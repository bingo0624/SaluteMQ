package com.bingo.salute.mq.logger.inner;

import com.bingo.salute.mq.logger.BasicLoggerTest;
import com.bingo.salute.mq.logger.InnerLoggerFactory;
import com.bingo.salute.mq.logger.InternalLogger;
import com.bingo.salute.mq.logger.InternalLoggerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class LoggerTest extends BasicLoggerTest {


    @Before
    public void init() {
        InternalLoggerFactory.setCurrentLoggerType(InnerLoggerFactory.LOGGER_INNER);
    }

    @Test
    public void testInnerConsoleLogger() throws IOException {
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Appender consoleAppender = LoggingBuilder.newAppenderBuilder()
                .withConsoleAppender(LoggingBuilder.SYSTEM_OUT)
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        Logger.getLogger("ConsoleLogger").addAppender(consoleAppender);
        Logger.getLogger("ConsoleLogger").setLevel(Level.INFO);

        InternalLogger consoleLogger1 = InternalLoggerFactory.getLogger("ConsoleLogger");
        consoleLogger1.info("console info Message");
        consoleLogger1.error("console error Message", new RuntimeException());
        consoleLogger1.debug("console debug message");

        consoleLogger1.info("console {} test", "simple");
        consoleLogger1.info("[WATERMARK] Send Queue Size: {} SlowTimeMills: {}", 1, 300);
        consoleLogger1.info("new consumer connected, group: {} {} {} channel: {}", "mygroup", "orderly",
                "broudcast", new RuntimeException("simple object"));

        System.setOut(out);
        consoleAppender.close();

        String result = new String(byteArrayOutputStream.toByteArray());

        System.out.println(result);

        Assert.assertTrue(result.contains("info"));
        Assert.assertTrue(result.contains("RuntimeException"));
        Assert.assertTrue(result.contains("WATERMARK"));
        Assert.assertTrue(result.contains("consumer"));
        Assert.assertTrue(result.contains("broudcast"));
        Assert.assertTrue(result.contains("simple test"));
        Assert.assertTrue(!result.contains("debug"));
    }

    @Test
    public void testInnerFileLogger() throws IOException {
        String file = loggingDir + "/inner.log";

        Logger fileLogger = Logger.getLogger("innerLogger");

        Appender myappender = LoggingBuilder.newAppenderBuilder()
                .withDailyFileRollingAppender(file, "'.'yyyy-MM-dd")
                .withName("innerAppender")
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        fileLogger.addAppender(myappender);
        fileLogger.setLevel(Level.INFO);

        InternalLogger innerLogger = InternalLoggerFactory.getLogger("innerLogger");

        innerLogger.info("fileLogger info Message");
        innerLogger.error("fileLogger error Message", new RuntimeException());
        innerLogger.debug("fileLogger debug message");

        myappender.close();

        String content = readFile(file);

        System.out.println(content);

        Assert.assertTrue(content.contains("info"));
        Assert.assertTrue(content.contains("RuntimeException"));
        Assert.assertTrue(!content.contains("debug"));
    }

    @After
    public void close() {
        InternalLoggerFactory.setCurrentLoggerType(null);
    }
}
