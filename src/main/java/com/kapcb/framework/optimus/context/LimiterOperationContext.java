package com.kapcb.framework.optimus.context;

import com.kapcb.framework.optimus.argument.ArgumentInjector;
import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.operation.LimiterOperation;
import com.kapcb.framework.optimus.operation.LimiterOperationMetadata;
import com.kapcb.framework.optimus.spel.CustomerEvaluationContext;
import com.kapcb.framework.optimus.spel.LimiterOperationExpressionEvaluator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title: LimiterOperationContext </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationContext <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:27
 * @since 1.0
 */
public class LimiterOperationContext implements LimiterOperationInvocationContext {

    private final BeanFactory beanFactory;
    private final Object target;
    private final Object[] args;
    private final Limiter limiter;
    private final String limiterName;
    private final LimiterOperationMetadata limiterOperationMetadata;
    private final LimiterOperationExpressionEvaluator limiterOperationExpressionEvaluator;

    public LimiterOperationContext(BeanFactory beanFactory, Object target, Object[] args, LimiterOperationMetadata limiterOperationMetadata, LimiterOperationExpressionEvaluator limiterOperationExpressionEvaluator) {
        this.beanFactory = beanFactory;
        this.target = target;
        this.args = args;
        this.limiter = limiterOperationMetadata.getLimiter(this);
        this.limiterName = this.createLimiterName(this.limiter);
        this.limiterOperationMetadata = limiterOperationMetadata;
        this.limiterOperationExpressionEvaluator = limiterOperationExpressionEvaluator;
    }

    private String createLimiterName(Limiter limiter) {
        return limiter.getLimiterName();
    }

    private Object[] extractArgs(Method method, Object[] args) {
        if (!method.isVarArgs()) {
            return args;
        } else {
            Object[] varArgs = ObjectUtils.toObjectArray(args[args.length - 1]);
            Object[] combinedArgs = new Object[args.length - 1 + varArgs.length];
            System.arraycopy(args, 0, combinedArgs, 0, args.length - 1);
            System.arraycopy(varArgs, 0, combinedArgs, args.length - 1, varArgs.length);
            return combinedArgs;
        }
    }

    @Override
    public LimiterOperation<? extends Limiter> getLimiterOperation() {
        return this.limiterOperationMetadata.getLimiterOperation();
    }

    @Override
    public Object getTarget() {
        return this.target;
    }

    @Override
    public Method getMethod() {
        return this.limiterOperationMetadata.getMethod();
    }

    @Override
    public Object[] getArgs() {
        return this.args;
    }

    public Map<String, Object> getCustomArgument() {
        return this.getLimiterOperation().getCustomArgument();
    }

    protected Object generateKey(Map<String, Object> injectArgs) {
        if (StringUtils.hasText(this.getLimiterOperation().getKey())) {
            CustomerEvaluationContext customerEvaluationContext = customerEvaluationContext(injectArgs);
            return limiterOperationExpressionEvaluator.key(this.limiterOperationMetadata.getLimiterOperation().getKey(), this.limiterOperationMetadata.getAnnotatedElementKey(), customerEvaluationContext);
        }
        return this.limiterOperationMetadata.getKeyGenerator().generate(this.target, this.limiterOperationMetadata.getMethod(), this.args);
    }

    public LimiterOperationMetadata getLimiterOperationMetadata() {
        return this.limiterOperationMetadata;
    }

    public Limiter getLimiter() {
        return this.limiter;
    }

    public Map<String, Object> generateInjectArgs() {
        Map<String, Object> returnValue = new HashMap<>(4);
        if (CollectionUtils.isEmpty(this.limiterOperationMetadata.getArgumentInjectors())) {
            return returnValue;
        }

        for (ArgumentInjector argumentInjector : this.limiterOperationMetadata.getArgumentInjectors()) {
            Map<String, Object> inject = argumentInjector.inject(this.args);
            if (MapUtils.isNotEmpty(inject)) {
                returnValue.putAll(inject);
            }
        }
        return returnValue;
    }

    private CustomerEvaluationContext customerEvaluationContext(Map<String, Object> injectArgs) {
        return limiterOperationExpressionEvaluator.createCustomerEvaluationContext(this.limiter, this.limiterOperationMetadata.getMethod(), this.args, this.target, this.limiterOperationMetadata.getTargetMethod(), this.limiterOperationMetadata.getTargetClass(), injectArgs, this.beanFactory);
    }

    protected boolean conditionPass(Map<String, Object> injectArgs) {
        if (StringUtils.hasText(this.limiterOperationMetadata.getLimiterOperation().getCondition())) {
            CustomerEvaluationContext customerEvaluationContext = customerEvaluationContext(injectArgs);
            return limiterOperationExpressionEvaluator.condition(this.limiterOperationMetadata.getLimiterOperation().getCondition(), this.limiterOperationMetadata.getAnnotatedElementKey(), customerEvaluationContext);
        }
        return false;
    }

}
