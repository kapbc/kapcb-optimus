package com.kapcb.framework.optimus.operation;

import com.kapcb.framework.optimus.argument.ArgumentInjector;
import com.kapcb.framework.optimus.context.LimiterOperationContext;
import com.kapcb.framework.optimus.handler.ErrorHandler;
import com.kapcb.framework.optimus.key.KeyGenerator;
import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.resolver.FallbackResolver;
import com.kapcb.framework.optimus.resolver.LimiterResolver;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * <a>Title: LimiterOperationMetadata </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationMetadata <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 16:21
 * @since 1.0
 */
public class LimiterOperationMetadata {

    private final FallbackResolver fallbackResolver;
    private final Collection<ArgumentInjector> argumentInjectors;
    private final ErrorHandler errorHandler;
    private final LimiterResolver limiterResolver;
    private final KeyGenerator keyGenerator;
    private final AnnotatedElementKey annotatedElementKey;
    private final Method targetMethod;
    private final Class<?> targetClass;
    private final Method method;
    private final LimiterOperation<? extends Limiter> limiterOperation;


    public LimiterOperationMetadata(LimiterOperation<? extends Limiter> limiterOperation, Method method, Class<?> targetClass, KeyGenerator keyGenerator, LimiterResolver limiterResolver, Collection<ArgumentInjector> argumentInjectors, FallbackResolver fallbackResolver, ErrorHandler errorHandler) {
        this.limiterOperation = limiterOperation;
        this.method = BridgeMethodResolver.findBridgedMethod(method);
        this.targetClass = targetClass;
        this.targetMethod = !Proxy.isProxyClass(targetClass) ? AopUtils.getMostSpecificMethod(method, targetClass) : this.method;
        this.annotatedElementKey = new AnnotatedElementKey(this.targetMethod, targetClass);
        this.keyGenerator = keyGenerator;
        this.limiterResolver = limiterResolver;
        this.argumentInjectors = argumentInjectors;
        this.fallbackResolver = fallbackResolver;
        this.errorHandler = errorHandler;
    }

    public Object resolveLimiterFail(Object[] args) {
        return this.fallbackResolver.resolve(this.method, this.targetClass, args, this.limiterOperation.getKey());
    }

    public ErrorHandler getErrorHandler(){
        return this.errorHandler;
    }

    public Method getMethod(){
        return this.method;
    }

    public Limiter getLimiter(LimiterOperationContext limiterOperationContext){
//        this.limiterResolver
    }

    public LimiterOperation<? extends Limiter> getLimiterOperation(){
        return this.limiterOperation;
    }

}
