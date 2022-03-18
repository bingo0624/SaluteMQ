package com.bingo.salute.mq.remoting.common;

/**
 * Author : bingo624
 * Date : 2022/3/15 21:35
 * Description :
 * version : 1.0
 */
public enum TlsMode {

    DISABLED("disabled"),
    PERMISSIVE("permissive"),
    ENFORCING("enforcing");

    private String name;

    TlsMode(String name) {
        this.name = name;
    }

    public static TlsMode parse(String mode) {
        for (TlsMode tlsMode : TlsMode.values()) {
            if (tlsMode.name.equals(mode)) {
                return tlsMode;
            }
        }

        return PERMISSIVE;
    }

    public String getName() {
        return name;
    }
}
