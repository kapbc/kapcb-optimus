package com.kapcb.framework.optimus.key;

import java.lang.reflect.Method;

/**
 * <a>Title: KeyGenerator </a>
 * <a>Author: Kapcb <a>
 * <a>Description: KeyGenerator <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:41
 * @since 1.0
 */
@FunctionalInterface
public interface KeyGenerator {

    Object generate(Object object, Method method, Object... args);

}
