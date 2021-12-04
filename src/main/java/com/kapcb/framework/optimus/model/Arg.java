package com.kapcb.framework.optimus.model;

/**
 * <a>Title: Arg </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Arg <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 20:53
 * @since 1.0
 */
public interface Arg<T> {

    T build();

    Object get(String name);

}
