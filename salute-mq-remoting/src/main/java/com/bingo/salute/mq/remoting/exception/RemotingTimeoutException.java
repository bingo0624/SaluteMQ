package com.bingo.salute.mq.remoting.exception;

/**
 * Author : bingo624
 * Date : 2022/3/18 21:25
 * Description :
 * version : 1.0
 */
public class RemotingTimeoutException extends RemotingException {

    public RemotingTimeoutException(String message) {
        super(message);
    }

    public RemotingTimeoutException(String addr, long timeoutMillis) {
        this(addr, timeoutMillis, null);
    }

    public RemotingTimeoutException(String addr, long timeoutMillis, Throwable cause) {
        super("wait response on the channel <" + addr + "> timeout, " + timeoutMillis + "(ms)", cause);
    }
}
