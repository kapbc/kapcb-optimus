package com.kapcb.framework.optimus.model;

import com.kapcb.framework.optimus.limit.Limiter;

import java.lang.reflect.Method;

/**
 * <a>Title: LimiterExpressionRootObject </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterExpressionRootObject <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:45
 * @since 1.0
 */
public class LimiterExpressionRootObject {

    private final Limiter limiter;
    private final Method method;
    private final Object[] args;
    private final Object target;
    private final Class<?> targetClass;

    public LimiterExpressionRootObject(Limiter limiter,
                                       Method method,
                                       Object[] args,
                                       Object target,
                                       Class<?> targetClass) {
        this.limiter = limiter;
        this.method = method;
        this.args = args;
        this.target = target;
        this.targetClass = targetClass;
    }

    public Limiter getLimiter() {
        return this.limiter;
    }

    public Method getMethod() {
        return this.method;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public Object getTarget() {
        return this.target;
    }

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

}
