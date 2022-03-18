package com.bingo.salute.mq.remoting;

import com.bingo.salute.mq.remoting.netty.NettyRemotingServer;
import com.bingo.salute.mq.remoting.netty.NettyServerConfig;
import com.bingo.salute.mq.remoting.protocol.RemotingCommand;
import com.bingo.salute.mq.remoting.service.NettyRequestProcessor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author : bingo624
 * Date : 2022/3/18 19:29
 * Description :
 * version : 1.0
 */
public class NettyServerTest {

    public static void main(String[] args) {
        NettyServerConfig serverConfig = new NettyServerConfig();
        NettyRemotingServer server = new NettyRemotingServer(serverConfig);

        NettyRequestProcessor processor = (ctx, request) -> {
            try {
                System.err.println(request);
                byte[] bytes = "小明上广州".getBytes("UTF-8");
                RemotingCommand responseCommand = RemotingCommand.createResponseCommand(2, "测试啊~");
                responseCommand.setBody(bytes);
                return responseCommand;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        };

        server.registerProcessor(2, processor, new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()));

        server.start();
    }
}
