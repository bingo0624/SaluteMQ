package com.bingo.salute.mq.remoting.exception;

/**
 * Author : bingo624
 * Date : 2022/3/18 23:34
 * Description :
 * version : 1.0
 */
public class RemotingSendRequestException extends RemotingException {

    public RemotingSendRequestException(String addr) {
        this(addr, null);
    }

    public RemotingSendRequestException(String addr, Throwable cause) {
        super("send request to <" + addr + "> failed", cause);
    }
}

