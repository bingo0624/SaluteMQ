package com.bingo.salute.mq.remoting.protocol;

/**
 * Author : HuangYong8046
 * Date : 2022/3/20 14:11
 * Description :
 * version : 1.0
 */
public enum SerializeType {
    JSON((byte) 0),
    ;
    private byte code;

    SerializeType(byte code) {
        this.code = code;
    }

    public static SerializeType valueOf(byte code) {
        for (SerializeType serializeType : SerializeType.values()) {
            if (serializeType.getCode() == code) {
                return serializeType;
            }
        }
        return null;
    }

    public byte getCode() {
        return code;
    }
}
