package com.bingo.salute.mq.remoting.service;

import io.netty.channel.Channel;

/**
 * Author : bingo624
 * Date : 2022/3/14 22:33
 * Description : channel事件监听
 * version : 1.0
 */
public interface ChannelEventListener {

    /**
     * 链接事件
     */
    void onChannelConnect(String remoteAddr, Channel channel);

    /**
     * 关闭事件
     */
    void onChannelClose(String remoteAddr, Channel channel);

    /**
     * 异常事件
     */
    void onChannelException(String remoteAddr, Channel channel);

    /**
     * 空闲检测事件
     */
    void onChannelIdle(String remoteAddr, Channel channel);
}
