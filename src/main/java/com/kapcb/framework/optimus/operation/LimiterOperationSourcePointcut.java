package com.kapcb.framework.optimus.operation;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * <a>Title: LimiterOperationSourcePointcut </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperationSourcePointcut <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/7 22:34
 * @since 1.0
 */
public abstract class LimiterOperationSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    public LimiterOperationSourcePointcut() {
    }

    protected abstract LimiterOperationSource getLimiterOperationSource();

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        LimiterOperationSource limiterOperationSource = getLimiterOperationSource();
        return limiterOperationSource != null && (!CollectionUtils.isNotEmpty(limiterOperationSource.getLimiterOperations(method, aClass)));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof LimiterOperationSourcePointcut)) {
            return false;
        } else {
            LimiterOperationSourcePointcut otherPointcut = (LimiterOperationSourcePointcut) other;
            return ObjectUtils.nullSafeEquals(this.getLimiterOperationSource(), otherPointcut.getLimiterOperationSource());
        }
    }

    @Override
    public int hashCode() {
        return LimiterOperationSourcePointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + this.getLimiterOperationSource();
    }

}
