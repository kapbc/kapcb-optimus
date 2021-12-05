package com.kapcb.framework.optimus.context;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.operation.LimiterOperation;

import java.lang.reflect.Method;

/**
 * <a>Title: LimiterOperationContext </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationContext <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:27
 * @since 1.0
 */
public class LimiterOperationContext implements LimiterOperationInvocationContext{

    @Override
    public LimiterOperation<? extends Limiter> getLimiterOperation() {
        return null;
    }

    @Override
    public Object getTarget() {
        return null;
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Object[] getArgs() {
        return new Object[0];
    }
}
