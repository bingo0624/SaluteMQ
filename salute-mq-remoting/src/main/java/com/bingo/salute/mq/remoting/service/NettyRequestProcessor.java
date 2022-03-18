package com.bingo.salute.mq.remoting.service;

import com.bingo.salute.mq.remoting.protocol.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;

/**
 * Author : bingo624
 * Date : 2022/3/14 21:32
 * Description : Netty请求处理器
 * version : 1.0
 */
public interface NettyRequestProcessor {

    /**
     * 处理请求
     */
    RemotingCommand processRequest(ChannelHandlerContext ctx, RemotingCommand request);
}
