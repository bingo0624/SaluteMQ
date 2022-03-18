package com.bingo.salute.mq.remoting.netty;

/**
 * Author : bingo624
 * Date : 2022/3/15 23:14
 * Description :
 * version : 1.0
 */

import com.bingo.salute.mq.remoting.common.SemaphoreReleaseOnlyOnce;
import com.bingo.salute.mq.remoting.protocol.RemotingCommand;
import com.bingo.salute.mq.remoting.service.InvokeCallback;
import io.netty.channel.Channel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 响应对象
 */
public class ResponseFuture {
    // 请求id
    private final int opaque;

    // 处理通道
    private final Channel processChannel;

    // 超时时间
    private final long timeoutMillis;

    // 执行回调
    private final InvokeCallback invokeCallback;

    // 开始时间戳
    private final long beginTimestamp = System.currentTimeMillis();

    //
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    // 信号量
    private final SemaphoreReleaseOnlyOnce once;

    // 是否只执行一次回调
    private final AtomicBoolean executeCallbackOnlyOnce = new AtomicBoolean(false);

    // 响应指令
    private volatile RemotingCommand responseCommand;

    // 发送请求状态
    private volatile boolean sendRequestOK = true;

    // 异常栈
    private volatile Throwable cause;

    public ResponseFuture(Channel channel, int opaque, long timeoutMillis, InvokeCallback invokeCallback,
                          SemaphoreReleaseOnlyOnce once) {
        this.opaque = opaque;
        this.processChannel = channel;
        this.timeoutMillis = timeoutMillis;
        this.invokeCallback = invokeCallback;
        this.once = once;
    }

    public void executeInvokeCallback() {
        if (invokeCallback != null) {
            if (this.executeCallbackOnlyOnce.compareAndSet(false, true)) {
                invokeCallback.operationComplete(this);
            }
        }
    }

    public void release() {
        if (this.once != null) {
            this.once.release();
        }
    }

    public boolean isTimeout() {
        long diff = System.currentTimeMillis() - this.beginTimestamp;
        return diff > this.timeoutMillis;
    }

    public RemotingCommand waitResponse(final long timeoutMillis) throws InterruptedException {
        this.countDownLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
        return this.responseCommand;
    }

    /**
     * 设置响应值, 并且放行 countDownLatch
     */
    public void putResponse(final RemotingCommand responseCommand) {
        this.responseCommand = responseCommand;
        this.countDownLatch.countDown();
    }

    public long getBeginTimestamp() {
        return beginTimestamp;
    }

    public boolean isSendRequestOK() {
        return sendRequestOK;
    }

    public void setSendRequestOK(boolean sendRequestOK) {
        this.sendRequestOK = sendRequestOK;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public InvokeCallback getInvokeCallback() {
        return invokeCallback;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public RemotingCommand getResponseCommand() {
        return responseCommand;
    }

    public void setResponseCommand(RemotingCommand responseCommand) {
        this.responseCommand = responseCommand;
    }

    public int getOpaque() {
        return opaque;
    }

    public Channel getProcessChannel() {
        return processChannel;
    }

    @Override
    public String toString() {
        return "ResponseFuture [responseCommand=" + responseCommand
                + ", sendRequestOK=" + sendRequestOK
                + ", cause=" + cause
                + ", opaque=" + opaque
                + ", processChannel=" + processChannel
                + ", timeoutMillis=" + timeoutMillis
                + ", invokeCallback=" + invokeCallback
                + ", beginTimestamp=" + beginTimestamp
                + ", countDownLatch=" + countDownLatch + "]";
    }
}

