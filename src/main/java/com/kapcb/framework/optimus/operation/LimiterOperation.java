package com.kapcb.framework.optimus.operation;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.model.Arg;
import com.kapcb.framework.optimus.model.Argument;

import java.util.Collection;
import java.util.Map;

/**
 * <a>Title: LimiterOperation </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperation <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 21:14
 * @since 1.0
 */
public abstract class LimiterOperation<T extends Limiter> implements Operation<LimiterOperation> {

    private final String name;

    private final String limiterName;

    private final String keyGenerator;

    private final String limiterManager;

    private final String key;

    private final String condition;

    private final String fallbackResolver;

    private final String errorHandler;

    private final Collection<String> argumentInjectors;

    private final Map<String, Object> customArgument;




    public abstract LimiterOperation buildOperation();

    @Override
    public LimiterOperation build() {
        return buildOperation();
    }
}
