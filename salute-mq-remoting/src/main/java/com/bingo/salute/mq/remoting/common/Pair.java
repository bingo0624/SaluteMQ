package com.bingo.salute.mq.remoting.common;

/**
 * Author : bingo624
 * Date : 2022/3/14 21:41
 * Description : 配对映射封装
 * version : 1.0
 */
public class Pair<T1, T2> {

    private T1 obj1;
    private T2 obje2;

    public Pair(T1 obj1, T2 obje2) {
        this.obj1 = obj1;
        this.obje2 = obje2;
    }

    public T1 getObj1() {
        return obj1;
    }

    public void setObj1(T1 obj1) {
        this.obj1 = obj1;
    }

    public T2 getObje2() {
        return obje2;
    }

    public void setObje2(T2 obje2) {
        this.obje2 = obje2;
    }
}
