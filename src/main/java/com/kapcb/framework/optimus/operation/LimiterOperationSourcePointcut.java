package com.kapcb.framework.optimus.operation;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

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

    public LimiterOperationSourcePointcut(){}

    @Override
    public boolean matches(Method method, Class<?> aClass) {

        return false;

    }

}
