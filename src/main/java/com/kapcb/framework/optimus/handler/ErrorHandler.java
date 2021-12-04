package com.kapcb.framework.optimus.handler;

/**
 * <a>Title: ErrorHandler </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ErrorHandler <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:41
 * @since 1.0
 */
public interface ErrorHandler extends Handler<RuntimeException> {

    @Override
    void handler(RuntimeException runtimeException);

}
