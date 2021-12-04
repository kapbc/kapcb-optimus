package com.kapcb.framework.optimus.resolver;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;

/**
 * <a>Title: FallbackResolver </a>
 * <a>Author: Kapcb <a>
 * <a>Description: FallbackResolver <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:55
 * @since 1.0
 */
public interface FallbackResolver extends Resolver<T> {

    T resolve(Method method, Class<?> clazz, Object[] args, String key);

}
