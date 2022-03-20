package com.bingo.salute.mq.remoting.common;

import com.bingo.salute.mq.logger.InternalLogger;
import com.bingo.salute.mq.logger.InternalLoggerFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

/**
 * Author : bingo624
 * Date : 2022/3/15 21:33
 * Description :
 * version : 1.0
 */
public class RemotingUtil {

    private static final InternalLogger log = InternalLoggerFactory.getLogger(RemotingHelper.ROCKETMQ_REMOTING);

    public static final String OS_NAME = System.getProperty("os.name");
    private static boolean isLinuxPlatform = false;
    private static boolean isWindowsPlatform = false;

    static {
        if (OS_NAME != null && OS_NAME.toLowerCase().contains("linux")) {
            isLinuxPlatform = true;
        }

        if (OS_NAME != null && OS_NAME.toLowerCase().contains("windows")) {
            isWindowsPlatform = true;
        }
    }

    public static boolean isLinuxPlatform() {
        return false;
    }

    public static void closeChannel(Channel channel) {
        final String addrRemote = RemotingHelper.parseChannelRemoteAddr(channel);
        channel.close().addListener((ChannelFutureListener) future ->
                log.info("closeChannel: close the connection to remote address[{}] result: {}", addrRemote, future.isSuccess())
        );
    }
}
