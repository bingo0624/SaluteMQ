package com.bingo.salute.mq.remoting.service;

/**
 * Author : bingo624
 * Date : 2022/3/14 21:19
 * Description : 远程调用服务接口
 * version : 1.0
 */
public interface RemotingService {

    /**
     * 启动
     */
    void start();

    /**
     * 关闭
     */
    void shutdown();

    /**
     * 注册调用前后钩子
     */
    void registerRPCHook(RPCHook rpcHook);
}
