package com.bingo.salute.mq.remoting.service;

import com.bingo.salute.mq.remoting.exception.RemotingConnectException;
import com.bingo.salute.mq.remoting.exception.RemotingSendRequestException;
import com.bingo.salute.mq.remoting.exception.RemotingTimeoutException;
import com.bingo.salute.mq.remoting.protocol.RemotingCommand;

import java.util.concurrent.ExecutorService;

/**
 * Author : bingo624
 * Date : 2022/3/14 21:31
 * Description : 远程调用客户端
 * version : 1.0
 */
public interface RemotingClient extends RemotingService {

    /**
     * 注册处理器
     */
    void registerProcessor(int requestCode, NettyRequestProcessor processor, ExecutorService executor);

    void setCallbackExecutor(final ExecutorService callbackExecutor);

    ExecutorService getCallbackExecutor();

    RemotingCommand invokeSync(String addr, RemotingCommand request, long timeoutMillis) throws InterruptedException, RemotingTimeoutException, RemotingSendRequestException, RemotingConnectException;
}
