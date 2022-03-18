package com.bingo.salute.mq.remoting.service;

import com.bingo.salute.mq.remoting.netty.ResponseFuture;

/**
 * Author : bingo624
 * Date : 2022/3/15 23:15
 * Description :
 * version : 1.0
 */
public interface InvokeCallback {
    void operationComplete(final ResponseFuture responseFuture);
}
