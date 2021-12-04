package com.kapcb.framework.optimus.handler;

/**
 * <a>Title: Handler </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Handler <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:49
 * @since 1.0
 */
public interface Handler<T> {

    /**
     * handler
     *
     * @param data T
     */
    void handler(T data);

}
