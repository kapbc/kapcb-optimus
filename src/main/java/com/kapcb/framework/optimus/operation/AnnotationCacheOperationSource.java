package com.kapcb.framework.optimus.operation;

import com.kapcb.framework.optimus.limit.Limiter;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: AnnotationCacheOperationSource </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AnnotationCacheOperationSource <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 14:50
 * @since 1.0
 */
public class AnnotationCacheOperationSource extends AbstractFallbackLimiterOperationSource implements Serializable {

    private final boolean publicMethodOnly;

    @Override
    protected Collection<LimiterOperation<? extends Limiter>> findLimiterOperations(Method method) {
        return null;
    }

    @Override
    protected Collection<LimiterOperation<? extends Limiter>> findLimiterOperations(Class<?> clazz) {
        return null;
    }

}
