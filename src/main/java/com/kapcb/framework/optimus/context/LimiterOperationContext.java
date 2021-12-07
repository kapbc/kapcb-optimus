package com.kapcb.framework.optimus.context;

import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.operation.LimiterOperation;
import com.kapcb.framework.optimus.operation.LimiterOperationMetadata;
import com.kapcb.framework.optimus.spel.LimiterOperationExpressionEvaluator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

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
        return this.limiterOperationMetadata.getLimiter();
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

}
