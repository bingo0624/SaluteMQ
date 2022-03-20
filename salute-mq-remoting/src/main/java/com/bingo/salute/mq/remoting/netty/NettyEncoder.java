package com.bingo.salute.mq.remoting.netty;

import com.bingo.salute.mq.logger.InternalLogger;
import com.bingo.salute.mq.logger.InternalLoggerFactory;
import com.bingo.salute.mq.remoting.common.RemotingHelper;
import com.bingo.salute.mq.remoting.common.RemotingUtil;
import com.bingo.salute.mq.remoting.protocol.RemotingCommand;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteBuffer;

/**
 * Author : bingo624
 * Date : 2022/3/14 23:02
 * Description :
 * version : 1.0
 */
public class NettyEncoder extends MessageToByteEncoder<RemotingCommand> {

    private static final InternalLogger log = InternalLoggerFactory.getLogger(RemotingHelper.ROCKETMQ_REMOTING);

    @Override
    protected void encode(ChannelHandlerContext ctx, RemotingCommand remotingCommand, ByteBuf out) throws Exception {
        try {
            ByteBuffer header = remotingCommand.encodeHeader();
            out.writeBytes(header);
            byte[] body = remotingCommand.getBody();
            if (body != null) {
                out.writeBytes(body);
            }
        } catch (Exception e) {
            log.error("encode exception, " + RemotingHelper.parseChannelRemoteAddr(ctx.channel()), e);
            if (remotingCommand != null) {
                log.error(remotingCommand.toString());
            }
            RemotingUtil.closeChannel(ctx.channel());
        }
    }
}
