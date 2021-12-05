package com.kapcb.framework.optimus.spel;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.model.ExpressionRootObject;
import org.springframework.beans.factory.BeanFactory;

import java.beans.Expression;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a>Title: LimiterOperationExpressionEvaluator </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationExpressionEvaluator <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:39
 * @since 1.0
 */
public class LimiterOperationExpressionEvaluator extends CustomerExpressionEvaluator {

    private final Map<ExpressionKey, Expression> keyCache = new ConcurrentHashMap<>(32);

    private final Map<ExpressionKey, Expression> conditionCache = new ConcurrentHashMap<>(32);

    private final Map<ExpressionKey, Expression> unlessCache = new ConcurrentHashMap<>(32);

    public CustomerEvaluationContext createCustomerEvaluationContext(Limiter limiter,
                                                                     Method method,
                                                                     Object[] args,
                                                                     Object target,
                                                                     Method targetMethod,
                                                                     Class<?> targetClass,
                                                                     Map<String, Object> injectArgs,
                                                                     BeanFactory beanFactory) {
        new ExpressionRootObject()
    }

    void clear() {
        this.keyCache.clear();
        this.conditionCache.clear();
        this.unlessCache.clear();
    }
}
