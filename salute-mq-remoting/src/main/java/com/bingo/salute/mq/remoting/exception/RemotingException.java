package com.bingo.salute.mq.remoting.exception;

/**
 * Author : bingo624
 * Date : 2022/3/18 21:25
 * Description :
 * version : 1.0
 */
public class RemotingException extends Exception {

    public RemotingException(String message) {
        super(message);
    }

    public RemotingException(String message, Throwable cause) {
        super(message, cause);
    }
}
