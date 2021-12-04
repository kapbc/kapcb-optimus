package com.kapcb.framework.optimus.spel;

import com.kapcb.framework.optimus.exception.VariableNotAvailableException;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * <a>Title: CustomerEvaluationContext </a>
 * <a>Author: Kapcb <a>
 * <a>Description: CustomerEvaluationContext <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:43
 * @since 1.0
 */
public class CustomerEvaluationContext extends MethodBasedEvaluationContext {

    private final Set<String> unavailableVariables = new HashSet<>(1);

    public CustomerEvaluationContext(Object rootObject, Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
    }

    public void addUnavailableVariable(String name) {
        this.unavailableVariables.add(name);
    }

    @Override
    public Object lookupVariable(String name) {
        if (this.unavailableVariables.contains(name)) {
            throw new VariableNotAvailableException(name);
        }
        return super.lookupVariable(name);
    }
}
