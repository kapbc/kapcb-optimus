package com.kapcb.framework.optimus.spel;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.model.LimiterExpressionRootObject;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
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
        LimiterExpressionRootObject limiterExpressionRootObject = new LimiterExpressionRootObject(limiter, method, args, target, targetClass);
        CustomerEvaluationContext customerEvaluationContext = new CustomerEvaluationContext(limiterExpressionRootObject, targetMethod, args, getParameterNameDiscoverer());
        if (MapUtils.isNotEmpty(injectArgs)) {
            for (String argKey : injectArgs.keySet()) {
                customerEvaluationContext.setVariable(argKey, injectArgs.get(argKey));
            }
        }

        if (Objects.nonNull(beanFactory)) {
            customerEvaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        }
        return customerEvaluationContext;
    }

    public Object key(String keyExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        Assert.hasLength(keyExpression, "key expression can not be null");
        return getExpression(keyExpression, annotatedElementKey, this.keyCache).getValue(evaluationContext);
    }

    public Boolean condition(String conditionExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        Assert.hasLength(conditionExpression, "condition expression can not be null");
        return getExpression(conditionExpression, annotatedElementKey, this.conditionCache).getValue(evaluationContext, Boolean.class);
    }

    public Boolean unless(String unlessExpression, AnnotatedElementKey annotatedElementKey, EvaluationContext evaluationContext) {
        Assert.hasLength(unlessExpression, "unless expression can not be null");
        return getExpression(unlessExpression, annotatedElementKey, this.unlessCache).getValue(evaluationContext, Boolean.class);
    }

    void clear() {
        this.keyCache.clear();
        this.conditionCache.clear();
        this.unlessCache.clear();
    }

}