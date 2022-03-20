package com.bingo.salute.mq.logger.inner;

public abstract class Layout {

    public abstract String format(LoggingEvent event);

    public String getContentType() {
        return "text/plain";
    }

    public String getHeader() {
        return null;
    }

    public String getFooter() {
        return null;
    }


    abstract public boolean ignoresThrowable();

}