package com.bingo.salute.mq.logger.inner;


import com.bingo.salute.mq.logger.BasicLoggerTest;
import org.junit.Assert;
import org.junit.Test;

public class LayoutTest extends BasicLoggerTest {

    @Test
    public void testSimpleLayout() {
        Layout layout = LoggingBuilder.newLayoutBuilder().withSimpleLayout().build();
        String format = layout.format(loggingEvent);
        Assert.assertTrue(format.contains("junit"));
    }

    @Test
    public void testDefaultLayout() {
        Layout layout = LoggingBuilder.newLayoutBuilder().withDefaultLayout().build();
        String format = layout.format(loggingEvent);
        String contentType = layout.getContentType();
        Assert.assertTrue(contentType.contains("text"));
        Assert.assertTrue(format.contains("createLoggingEvent"));
        Assert.assertTrue(format.contains("createLogging error"));
        Assert.assertTrue(format.contains(Thread.currentThread().getName()));
    }

    @Test
    public void testLogFormat() {
        Layout innerLayout = LoggingBuilder.newLayoutBuilder().withDefaultLayout().build();

        LoggingEvent loggingEvent = new LoggingEvent(Logger.class.getName(), logger, com.bingo.salute.mq.logger.inner.Level.INFO,
                "junit test error", null);
        String format = innerLayout.format(loggingEvent);

        System.out.println(format);
    }
}
