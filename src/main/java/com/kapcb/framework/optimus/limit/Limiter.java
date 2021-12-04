package com.kapcb.framework.optimus.limit;

import com.kapcb.framework.optimus.Behavior;
import com.kapcb.framework.optimus.model.Arg;
import org.apache.poi.ss.formula.functions.T;


/**
 * <a>Title: Limiter </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Limiter <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 20:47
 * @since 1.0
 */
public interface Limiter extends Behavior {

    String getLimiterName();

    boolean limit(Object key, Arg<T> arg);

    void release(Object key, Arg<T> arg);

}
