package com.kapcb.framework.optimus.parser;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.operation.LimiterOperation;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: AbstractLimiterAnnotationParser </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractLimiterAnnotationParser <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 14:58
 * @since 1.0
 */
public abstract class AbstractLimiterAnnotationParser<T extends Limiter> implements LimiterAnnotationParser<T> {

    public abstract Collection<LimiterOperation<? extends Limiter>> parseAnnotations(AnnotatedElement annotatedElement);

    @Override
    public Collection<LimiterOperation<? extends Limiter>> parseLimiterAnnotations(Method method) {
        return parseAnnotations(method);
    }

    @Override
    public Collection<LimiterOperation<? extends Limiter>> parseLimiterAnnotations(Class<?> clazz) {
        return parseAnnotations(clazz);
    }
}
