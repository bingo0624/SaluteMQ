package com.bingo.salute.mq.remoting.service;

import java.util.concurrent.ExecutorService;

/**
 * Author : bingo624
 * Date : 2022/3/14 21:35
 * Description : 远程调用服务端
 * version : 1.0
 */
public interface RemotingServer extends RemotingService {

    /**
     * 注册处理器
     */
    void registerProcessor(int requestCode, NettyRequestProcessor processor, ExecutorService executor);

    /**
     * 注册默认处理器
     */
    void registerDefaultProcessor(NettyRequestProcessor processor, ExecutorService executor);
}
