package com.kapcb.framework.optimus.context;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.operation.LimiterOperation;

import java.lang.reflect.Method;

/**
 * <a>Title: LimiterOperationInvocationContext </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationInvocationContext <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:27
 * @since 1.0
 */
public interface LimiterOperationInvocationContext {

    LimiterOperation<? extends Limiter> getLimiterOperation();

    Object getTarget();

    Method getMethod();

    Object[] getArgs();

}
