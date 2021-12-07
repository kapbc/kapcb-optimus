package com.kapcb.framework.optimus.resolver;

import com.kapcb.framework.optimus.context.LimiterOperationInvocationContext;
import com.kapcb.framework.optimus.operation.LimiterOperation;

/**
 * <a>Title: LimiterResolver </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterResolver <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:57
 * @since 1.0
 */
public interface LimiterResolver<T extends LimiterOperation> {

    T resolveLimiter(LimiterOperationInvocationContext<T> limiterOperationInvocationContext);

}
