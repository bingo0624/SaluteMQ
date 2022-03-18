package com.bingo.salute.mq.remoting.netty;

import lombok.Data;

/**
 * Author : bingo624
 * Date : 2022/3/14 22:07
 * Description : netty客户端配置
 * version : 1.0
 */
@Data
public class NettyClientConfig implements Cloneable {

    // 客户端工作线程数
    private int clientWorkerThreads = 4;

    // 客户端回调线程数
    private int clientCallbackExecutorThreads = Runtime.getRuntime().availableProcessors();

    // 链接超时时间
    private int connectTimeoutMillis = 3000;

    // 不活跃检测
    private long channelNotActiveInterval = 1000 * 60;

    // 服务端最大空闲时间
    private int clientChannelMaxIdleTimeSeconds = 120;

    // 客户端socket发送缓存区大小
    private int clientSocketSndBufSize = 65535;

    // 客户端socket接收缓冲区大小
    private int clientSocketRcvBufSize = 65535;

    private boolean clientCloseSocketIfTimeout = false;
}
