package com.bingo.salute.mq.remoting.service;

import com.bingo.salute.mq.remoting.protocol.RemotingCommand;

/**
 * Author : bingo624
 * Date : 2022/3/14 21:21
 * Description : RPC调用钩子
 * version : 1.0
 */
public interface RPCHook {

    /**
     * 请求之前处理
     */
    void doBeforeRequest(String remoteAddr, RemotingCommand request);

    /**
     * 请求之后处理
     */
    void doAfterResponse(String remoteAddr, RemotingCommand request, RemotingCommand response);
}
