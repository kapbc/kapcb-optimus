package com.kapcb.framework.optimus.parser;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.operation.LimiterOperation;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: LimiterAnnotationParser </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterAnnotationParser <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 14:53
 * @since 1.0
 */
public interface LimiterAnnotationParser<T extends Limiter> {

    Collection<LimiterOperation<? extends Limiter>> parseLimiterAnnotations(Method method);

    Collection<LimiterOperation<? extends Limiter>> parseLimiterAnnotations(Class<?> clazz);

}
