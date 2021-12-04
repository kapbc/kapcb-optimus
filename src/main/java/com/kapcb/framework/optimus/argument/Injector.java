package com.kapcb.framework.optimus.argument;

/**
 * <a>Title: Injector </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Injector <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:47
 * @since 1.0
 */
public interface Injector<T> {

    /**
     * injector's inject
     *
     * @param objects Object...
     * @return T
     */
    T inject(Object... objects);

}
