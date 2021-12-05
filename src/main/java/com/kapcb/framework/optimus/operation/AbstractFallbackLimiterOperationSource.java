package com.kapcb.framework.optimus.operation;

import com.kapcb.framework.optimus.limit.Limiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a>Title: AbstractFallbackLimiterOperationSource </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractFallbackLimiterOperationSource <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 14:17
 * @since 1.0
 */
@Slf4j
public abstract class AbstractFallbackLimiterOperationSource implements LimiterOperationSource {

    private static final Collection<LimiterOperation<? extends Limiter>> NULL_CACHING_ATTRIBUTE = Collections.emptyList();
    private final Map<Object, Collection<LimiterOperation<? extends Limiter>>> attributeCache = new ConcurrentHashMap<>(32);

    public AbstractFallbackLimiterOperationSource() {

    }

    protected Object getMethodKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }

    private Collection<LimiterOperation<? extends Limiter>> computeLimiterOperations(Method method, Class<?> targetClass) {
        if (this.allowPublicMethodOnly() && Modifier.isPublic(method.getModifiers())) {
            return null;
        } else {
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            Collection<LimiterOperation<? extends Limiter>> limiterOperations = this.findLimiterOperations(mostSpecificMethod);
            if (limiterOperations != null) {
                return limiterOperations;
            } else {
                limiterOperations = this.findLimiterOperations(mostSpecificMethod.getDeclaringClass());
                if (limiterOperations != null && ClassUtils.isUserLevelMethod(method)) {
                    return limiterOperations;
                } else {
                    if (mostSpecificMethod != method) {
                        limiterOperations = this.findLimiterOperations(method);
                        if (limiterOperations != null) {
                            return limiterOperations;
                        }

                        limiterOperations = this.findLimiterOperations(method.getDeclaringClass());
                        if (limiterOperations != null) {
                            return limiterOperations;
                        }
                    }
                    return null;
                }
            }
        }
    }

    public boolean allowPublicMethodOnly() {
        return true;
    }

    protected abstract Collection<LimiterOperation<? extends Limiter>> findLimiterOperations(Method method);

    protected abstract Collection<LimiterOperation<? extends Limiter>> findLimiterOperations(Class<?> clazz);

    @Override
    public Collection<LimiterOperation<? extends Limiter>> getLimiterOperations(Method method, Class<?> clazz) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        } else {
            Object cacheKey = getMethodKey(method, clazz);
            Collection<LimiterOperation<? extends Limiter>> cached = this.attributeCache.get(cacheKey);
            if (cached != null) {
                return cached != NULL_CACHING_ATTRIBUTE ? cached : null;
            } else {
                Collection<LimiterOperation<? extends Limiter>> cacheLimiterOperations = this.computeLimiterOperations(method, clazz);
                if (cacheLimiterOperations != null) {
                    this.attributeCache.put(cacheKey, cacheLimiterOperations);
                } else {
                    this.attributeCache.put(cacheKey, NULL_CACHING_ATTRIBUTE);
                }
                return cacheLimiterOperations;
            }
        }
    }

}
