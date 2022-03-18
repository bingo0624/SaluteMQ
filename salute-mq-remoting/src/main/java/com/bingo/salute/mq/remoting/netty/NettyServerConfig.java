package com.bingo.salute.mq.remoting.netty;

import lombok.Data;

/**
 * Author : bingo624
 * Date : 2022/3/14 22:10
 * Description : netty服务端配置
 * version : 1.0
 */
@Data
public class NettyServerConfig implements Cloneable {

    // 监听端口
    private int listenPort = 8888;

    // 业务线程池个数
    private int serverWorkThreads = 8;

    // 服务端最大空闲时间
    private int serverChannelMaxIdleTimeSeconds = 120;

    // Netty public 任务线程池线程个数（业务类型未注册线程池，则交由 public 任务线程池处理）
    private int serverCallbackExecutorThreads = 0;

    // IO线程池线程个数，主要处理网络请求、解析请求包；派发到各业务线程池完成具体的业务操作
    private int serverSelectorThreads = 3;

    // 网络Socket发送缓存区大小，默认64K
    private int serverSocketSndBufSize = 65535;

    // 网络Socket接收缓存区大小，默认64K
    private int serverSocketRcvBufSize = 65535;

    // ByteBuffer 是否开启缓存
    private boolean serverPooledByteBufAllocatorEnable = true;

    // 是否启用 Epoll IO 模型
    private boolean useEpollNativeSelector = false;
}
