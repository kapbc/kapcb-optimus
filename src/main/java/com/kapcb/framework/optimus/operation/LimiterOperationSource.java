package com.kapcb.framework.optimus.operation;

import com.kapcb.framework.optimus.limit.Limiter;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * <a>Title: LimiterOperationSource </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationSource <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 14:12
 * @since 1.0
 */
public interface LimiterOperationSource {

    Collection<LimiterOperation<? extends Limiter>> getLimiterOperations(Method method, Class<?> clazz);

}
