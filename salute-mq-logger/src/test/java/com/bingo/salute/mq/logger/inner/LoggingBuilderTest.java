package com.bingo.salute.mq.logger.inner;


import com.bingo.salute.mq.logger.BasicLoggerTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;

public class LoggingBuilderTest extends BasicLoggerTest {

    @Test
    public void testConsole() {
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Appender consoleAppender = LoggingBuilder.newAppenderBuilder()
                .withConsoleAppender(LoggingBuilder.SYSTEM_OUT)
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();
        consoleAppender.doAppend(loggingEvent);
        String result = new String(byteArrayOutputStream.toByteArray());
        System.setOut(out);

        Assert.assertTrue(result.contains(loggingEvent.getMessage().toString()));

    }

    @Test
    public void testFileAppender() throws InterruptedException {
        String logFile = loggingDir + "/file.log";
        Appender rollingFileAppender = LoggingBuilder.newAppenderBuilder().withAsync(false, 102400)
                .withFileAppender(logFile).withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        for (int i = 0; i < 10; i++) {
            rollingFileAppender.doAppend(loggingEvent);
        }
        rollingFileAppender.close();

        File file = new File(logFile);
        Assert.assertTrue(file.length() > 0);
    }

    @Test
    public void testRollingFileAppender() throws InterruptedException {

        String rollingFile = loggingDir + "/rolling.log";
        Appender rollingFileAppender = LoggingBuilder.newAppenderBuilder().withAsync(false, 1024)
                .withRollingFileAppender(rollingFile, "1024", 5)
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        for (int i = 0; i < 100; i++) {
            rollingFileAppender.doAppend(loggingEvent);
        }
        rollingFileAppender.close();

        int cc = 0;
        for (int i = 0; i < 5; i++) {
            File file;
            if (i == 0) {
                file = new File(rollingFile);
            } else {
                file = new File(rollingFile + "." + i);
            }
            if (file.exists() && file.length() > 0) {
                cc += 1;
            }
        }
        Assert.assertTrue(cc >= 2);
    }

    //@Test
    public void testDailyRollingFileAppender() throws InterruptedException {
        String rollingFile = loggingDir + "/daily-rolling--222.log";
        Appender rollingFileAppender = LoggingBuilder.newAppenderBuilder().withAsync(false, 1024)
                .withDailyFileRollingAppender(rollingFile, "'.'yyyy-MM-dd_HH-mm-ss-SSS")
                .withLayout(LoggingBuilder.newLayoutBuilder().withDefaultLayout().build()).build();

        for (int i = 0; i < 100; i++) {
            rollingFileAppender.doAppend(loggingEvent);
        }

        rollingFileAppender.close();

        File file = new File(loggingDir);
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("daily-rolling--222.log");
            }
        });
        Assert.assertTrue(list.length > 0);
    }
}
