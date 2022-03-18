package com.bingo.salute.mq.remoting.exception;

/**
 * Author : bingo624
 * Date : 2022/3/18 23:38
 * Description :
 * version : 1.0
 */
public class RemotingConnectException extends RemotingException {

    public RemotingConnectException(String addr) {
        this(addr, null);
    }

    public RemotingConnectException(String addr, Throwable cause) {
        super("connect to <" + addr + "> failed", cause);
    }
}
