package com.bingo.salute.mq.logger.inner;

import com.bingo.salute.mq.logger.BasicLoggerTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;

public class LoggerRepositoryTest extends BasicLoggerTest {

    @Test
    public void testLoggerRepository() {
        Logger.getRepository().setLogLevel(Level.INFO);

        String file = loggingDir + "/repo.log";
        Logger fileLogger = Logger.getLogger("repoLogger");

        Appender myappender = LoggingBuilder.newAppenderBuilder()
                .withDailyFileRollingAppender(file, "'.'yyyy-MM-dd")
                .withName("repoAppender")
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        fileLogger.addAppender(myappender);
        Logger.getLogger("repoLogger").setLevel(Level.INFO);
        Logger repoLogger = Logger.getRepository().exists("repoLogger");
        Assert.assertTrue(repoLogger != null);
        Enumeration currentLoggers = Logger.getRepository().getCurrentLoggers();
        Level logLevel = Logger.getRepository().getLogLevel();
        Assert.assertTrue(logLevel.equals(Level.INFO));
//        Logger.getRepository().shutdown();
    }
}
